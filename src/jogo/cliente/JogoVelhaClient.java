package jogo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaClient extends Thread {

    private JogoVelhaClientListener listener;
    public final JogoVelhaClientConnection clientConnection;
    private final JogoVelhaClientView view;
    private boolean isRunning;

    public JogoVelhaClient(String serverAddress, int serverPort, JogoVelhaClientView view) throws IOException {
        this.view = view;
        this.clientConnection = new JogoVelhaClientConnection(serverAddress, serverPort);
        this.isRunning = true;
    }

    @Override
    public final void run() {
        try {
            while (this.isRunning) {
                this.listener = new JogoVelhaClientListener(this.clientConnection);
                Thread thread = new Thread(this.listener);
                thread.start();
                thread.join();
                JogoVelhaServerMessage response = this.listener.getResponse();
                this.view.updateView(response);
            }
        } catch (IOException ex) {
            Logger.getLogger(JogoVelhaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(JogoVelhaClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() throws IOException {
        this.isRunning = false;
        this.clientConnection.closeConnection();
        this.interrupt();
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
