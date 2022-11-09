package jogo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogoVelhaServer extends Thread {

    private final ServerSocket server;
    protected JogoVelhaServerClientsHandler clientsHandler;
    protected final JogoVelha game;
    protected volatile boolean isRunning;

    public JogoVelhaServer(int porta) throws IOException {
        this.game = new JogoVelha(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.server = new ServerSocket(porta);
        this.clientsHandler = new JogoVelhaServerClientsHandler();
        this.isRunning = true;
        System.out.println("Server is running on port: " + server.getLocalPort());
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                Socket socket;
                socket = this.server.accept();
                JogoVelhaServerConnection cliente = new JogoVelhaServerConnection(socket);
                this.clientsHandler.novoCliente(cliente);
                (new JogoVelhaServerHandler(cliente, this)).start();
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
            this.server.close();
            System.out.println("Server stopped to run.");
        } finally {
            super.finalize();
        }
    }
}
