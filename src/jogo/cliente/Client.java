package jogo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.ServerMessage;

public class Client extends Thread {

    private final ClientView view;
    protected final ClientConnection clientConnection;
    private ClientListener listener;
    public ClientHandler handler;
    private volatile boolean isRunning;

    public Client(String serverAddress, int serverPort, ClientView view) throws IOException {
        this.view = view;
        this.clientConnection = new ClientConnection(serverAddress, serverPort);
        this.handler = new ClientHandler(this.clientConnection);
        this.isRunning = true;
    }

    private ServerMessage getServerResponse() throws IOException, InterruptedException {
        this.listener = new ClientListener(this.clientConnection);
        Thread thread = new Thread(this.listener);
        thread.start();
        thread.join(); // waits for thread be resolved
        ServerMessage response = this.listener.getResponse();

        return response;
    }

    @Override
    public final void run() {
        try {
            while (this.isRunning) {
                ServerMessage response = this.getServerResponse();
                if (response.getShouldDisconnect()) {
                    break;
                }
                this.view.updateView(response);
            }
            this.view.disconnectClient();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
