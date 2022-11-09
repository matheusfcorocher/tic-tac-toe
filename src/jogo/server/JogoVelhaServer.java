package jogo.server;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogoVelhaServer extends Thread {

    protected JogoVelhaServerHandler serverHandler;
    protected JogoVelhaServerClientsHandler clientsHandler;
    protected final JogoVelha game;
    protected volatile boolean isRunning;

    public JogoVelhaServer(int port) throws IOException {
        this.game = new JogoVelha(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.clientsHandler = new JogoVelhaServerClientsHandler();
        this.serverHandler = new JogoVelhaServerHandler();
        this.serverHandler.create(port);
        this.isRunning = true;
        System.out.println("Server is running on port: " + serverHandler.getServer().getLocalPort());
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                if (this.clientsHandler.isClientsFull()) {
                    Socket socket;
                    socket = this.serverHandler.acceptClient();
                    JogoVelhaServerConnection client = new JogoVelhaServerConnection(socket);
                    this.clientsHandler.add(client);
                    (new JogoVelhaServerClientHandler(client, this)).start();
                }
            } catch (IOException ex) {
                if (ex.getMessage().contentEquals("Socket closed")) {

                } else {
                    Logger.getLogger(JogoVelhaServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public synchronized void finish() throws Throwable {
        this.finalize();
    }

    @Override
    protected synchronized void finalize() throws Throwable {
        try {
            this.isRunning = false;
            this.serverHandler.close();
            System.out.println("Server stopped to run.");
        } finally {
            super.finalize();
        }
    }
}
