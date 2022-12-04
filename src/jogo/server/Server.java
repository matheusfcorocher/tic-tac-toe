package jogo.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.ClientMessage;
import jogo.lib.ServerMessage;

public class Server extends Thread {

    private ServerView view;
    protected ServerHandler serverHandler;
    protected ClientsHandler clientsHandler;
    private JogoVelha game;
    private ServerDispatcher dispatcher;
    protected volatile boolean isRunning;
    private ResetElectionManager resetElectionManager;

    public Server(int port, ServerView view) throws IOException {
        this.view = view;
        this.game = new JogoVelha();
        this.clientsHandler = new ClientsHandler();
        this.dispatcher = new ServerDispatcher(this.clientsHandler);
        this.serverHandler = new ServerHandler();
        this.serverHandler.create(port);
        this.resetElectionManager = new ResetElectionManager();
        this.isRunning = true;
        System.out.println("Server is running on port: " + serverHandler.getServer().getLocalPort());
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                if (this.clientsHandler.isClientsNotFull()) {
                    Socket socket;
                    socket = this.serverHandler.acceptClient();
                    ServerConnection client = new ServerConnection(socket);
                    this.clientsHandler.add(client);
                    this.startGame(client);
                } else {
                    this.serverHandler.close(); //server stop listening for new clients
                }
            } catch (IOException ex) {
                try {
                    this.serverHandler.close();
                } catch (IOException ex1) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    private ClientMessage getClientRequest(ServerConnection client) throws IOException, InterruptedException {
        ServerListener serverListener = new ServerListener(client);
        Thread thread = new Thread(serverListener);
        thread.start();
        thread.join(); //waits for thread be resolved

        ClientMessage request = serverListener.getRequest();
        return request;
    }

    private void runPlayerVoteEvent(int player, boolean wantsReset) {
        if (this.game.isGameOver) {
            this.resetElectionManager.addVote(player, wantsReset);
        }
    }

    private boolean runElectionEvent() throws IOException {
        //call election to reset
        boolean disconnect = false;
        if (this.resetElectionManager.isReadyToCallElection()) {
            boolean reset = this.resetElectionManager.callResetElection();
            this.resetElectionManager.resetElection();

            this.game = new JogoVelha();
            ServerMessage response = this.game.getGameStatus();
            if (!reset) {
                response.setShouldDisconnect(true);
                disconnect = true;
            }

            this.dispatcher.dispatchMessageToAllClients(response);
        }

        return disconnect;
    }

    private void runPlayerMoveEvent(int player, ClientMessage request) throws IOException {
        ServerMessage response = this.game.execute(player, request.getInput());
        this.dispatcher.dispatchMessageToAllClients(response);
    }

    public void startGame(ServerConnection client) {
        new Thread(() -> {
            try {
                this.dispatcher.dispatchMessageToClient(client, this.game.getGameStatus());
                int playersQuantity = this.clientsHandler.getClients().size();
                this.resetElectionManager.initVotes(playersQuantity);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (this.isRunning) {
                    ClientMessage request = this.getClientRequest(client);

                    if (request == null) {
                        break;
                    }
                    int p = this.clientsHandler.getClientPosition(client);

                    this.runPlayerVoteEvent(p, request.getWantsReset());

                    if (this.resetElectionManager.didPlayerVote(p)) {
                        boolean disconnect = this.runElectionEvent();
                        if (disconnect) {
                            break;
                        }
                    } else {
                        this.runPlayerMoveEvent(p, request);
                    }
                }
                this.clientsHandler.remove(client);
                if (this.clientsHandler.getClients().isEmpty()) {
                    try {
                        this.finish();
                    } catch (Throwable ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();//interrupt listener thread
                return;
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    //separate events logic, add disconnect messages in client
    public synchronized void finish() throws Throwable {
        ServerMessage response = this.game.getGameStatus();
        response.setShouldDisconnect(true);
        this.dispatcher.dispatchMessageToAllClients(response);
        this.finalize();
        this.view.disconnectServer();
    }

    @Override
    protected synchronized void finalize() throws Throwable {
        try {
            this.isRunning = false;
            this.serverHandler.close();
            this.interrupt(); //stop main thread
            System.out.println("Server stopped to run.");
        } finally {
            super.finalize();
        }
    }
}
