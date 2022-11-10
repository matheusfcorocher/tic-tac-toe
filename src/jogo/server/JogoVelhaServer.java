    package jogo.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaServer extends Thread {

    protected JogoVelhaServerHandler serverHandler;
    protected JogoVelhaServerClientsHandler clientsHandler;
    protected final JogoVelha game;
    private final JogoVelhaServerDispatcher dispatcher;
    protected volatile boolean isRunning;

    public JogoVelhaServer(int port) throws IOException {
        this.game = new JogoVelha();
        this.clientsHandler = new JogoVelhaServerClientsHandler();
        this.dispatcher = new JogoVelhaServerDispatcher(this.clientsHandler);
        this.serverHandler = new JogoVelhaServerHandler();
        this.serverHandler.create(port);
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
                }
            } catch (IOException ex) {
                if (ex.getMessage().contentEquals("Socket closed")) {

                } else {
                    Logger.getLogger(JogoVelhaServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void startGame(JogoVelhaServerConnection client) {
        new Thread(() -> {
            try {
                this.dispatcher.dispatchMessageToClient(client, this.game.getGameStatus());
            } catch (IOException ex) {
                Logger.getLogger(JogoVelhaServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (this.isRunning) {
                    JogoVelhaServerListener serverListener = new JogoVelhaServerListener(client);
                    Thread thread = new Thread(serverListener);
                    thread.start();
                    thread.join(); //waits for thread be resolved

                    String request = serverListener.getRequest();
                    if (request == null || request.equals("")) {
                        break;
                    }
                    int q = Integer.parseInt(request);
                    int p = this.clientsHandler.getClientPosition(client);

                    JogoVelhaServerMessage response = this.game.execute(p, q);
                    this.dispatcher.dispatchMessageToAllClients(response);
                }
                this.clientsHandler.remove(client);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();//interrupt listener thread
                return;
            } catch (IOException ex) {
                Logger.getLogger(JogoVelhaServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public synchronized void finish() throws Throwable {
        this.finalize();
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
