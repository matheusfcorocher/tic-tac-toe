package jogo.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaClientMessage;
import jogo.lib.JogoVelhaServerMessage;

public class Server extends Thread {

    private ServerView view;
    protected JogoVelhaServerHandler serverHandler;
    protected JogoVelhaServerClientsHandler clientsHandler;
    private JogoVelha game;
    private JogoVelhaServerDispatcher dispatcher;
    protected volatile boolean isRunning;
    private ResetElectionManager resetElectionManager;

    public Server(int port, ServerView view) throws IOException {
        this.view = view;
        this.game = new JogoVelha();
        this.clientsHandler = new JogoVelhaServerClientsHandler();
        this.dispatcher = new JogoVelhaServerDispatcher(this.clientsHandler);
        this.serverHandler = new JogoVelhaServerHandler();
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
                    JogoVelhaServerConnection client = new JogoVelhaServerConnection(socket);
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

    public void startGame(JogoVelhaServerConnection client) {
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
                    JogoVelhaServerListener serverListener = new JogoVelhaServerListener(client);
                    Thread thread = new Thread(serverListener);
                    thread.start();
                    thread.join(); //waits for thread be resolved

                    JogoVelhaClientMessage request = serverListener.getRequest();
                    if (request == null) {
                        break;
                    }
                    int input = request.getInput();
                    int p = this.clientsHandler.getClientPosition(client);
                    boolean wantsReset = request.getWantsReset();

                    if (this.game.isThereAnyWinner(this.game.getWinner())) {
                        this.resetElectionManager.addVote(wantsReset, p);
                        if (this.resetElectionManager.isReadyToCallElection()) {
                            boolean reset = this.resetElectionManager.callResetElection();
                            this.game = new JogoVelha();
                            JogoVelhaServerMessage response = this.game.getGameStatus();
                            if (reset) {
                                this.dispatcher.dispatchMessageToAllClients(response);
                            } else {
                                response.setShouldDisconnect(true);
                                this.dispatcher.dispatchMessageToAllClients(response);
                                break;
                            }
                            this.resetElectionManager.resetElection();
                        }
                    } else {
                        JogoVelhaServerMessage response = this.game.execute(p, input);
                        this.dispatcher.dispatchMessageToAllClients(response);
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
