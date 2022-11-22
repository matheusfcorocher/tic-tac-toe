package jogo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.ServerMessage;

public class JogoVelhaClient extends Thread {

    private final ClientView view;
    protected final ClientConnection clientConnection;
    private JogoVelhaClientListener listener;
    public JogoVelhaClientHandler handler;
    private volatile boolean isRunning;

    public JogoVelhaClient(String serverAddress, int serverPort, ClientView view) throws IOException {
        this.view = view;
        this.clientConnection = new ClientConnection(serverAddress, serverPort);
        this.handler = new JogoVelhaClientHandler(this.clientConnection);
        this.isRunning = true;
    }

    @Override
    public final void run() {
        try {
            while (this.isRunning) {
                this.listener = new JogoVelhaClientListener(this.clientConnection);
                Thread thread = new Thread(this.listener);
                thread.start();
                thread.join(); // waits for thread be resolved
                ServerMessage response = this.listener.getResponse();
                if(response.getShouldDisconnect()) {
                    break;
                }
                this.view.updateView(response);
            }
            this.view.disconnectClient();
        } catch (IOException ex) {
            Logger.getLogger(JogoVelhaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();//interrupt listener thread
            return;
        }
    }

    public void close() throws IOException {
        this.isRunning = false;
        this.clientConnection.closeConnection();
        this.interrupt(); //stop main thread
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.close();
        } finally {
            super.finalize();
        }
    }
}
