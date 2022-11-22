package jogo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.ServerMessage;

public class JogoVelhaClientListener implements Runnable {

    private final JogoVelhaClientConnection clientConnection;
    private ServerMessage response;

    public JogoVelhaClientListener(JogoVelhaClientConnection clientConnection) throws IOException {
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
                Logger.getLogger(JogoVelhaClientListener.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogoVelhaClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerMessage getResponse() {
        return this.response;
    }
}
