package jogo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.ServerMessage;

public class ClientListener implements Runnable {

    private final ClientConnection clientConnection;
    private ServerMessage response;

    public ClientListener(ClientConnection clientConnection) throws IOException {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        try {
            if (this.clientConnection.isConnected() && this.clientConnection.isInputStreamNotEmpty()) {
                this.response = (ServerMessage) this.clientConnection.readMessage();
            }
        } catch (IOException ex) {
            try {
                this.clientConnection.closeConnection();
            } catch (IOException ex1) {
                Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerMessage getResponse() {
        return this.response;
    }
}
