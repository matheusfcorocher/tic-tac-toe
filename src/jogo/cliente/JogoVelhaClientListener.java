package jogo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaClientListener implements Runnable {

    private final JogoVelhaClientConnection clientConnection;
    private JogoVelhaServerMessage response;

    public JogoVelhaClientListener(JogoVelhaClientConnection clientConnection) throws IOException {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        try {
            if (this.clientConnection.socket.isConnected() && this.clientConnection.objectInputStream != null) {
                this.response = (JogoVelhaServerMessage) this.clientConnection.objectInputStream.readObject();
            }
        } catch (IOException ex) {
            Logger.getLogger(JogoVelhaClientListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JogoVelhaClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JogoVelhaServerMessage getResponse() {
        return this.response;
    }
}
