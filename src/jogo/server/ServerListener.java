package jogo.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaClientMessage;

public class ServerListener implements Runnable {

    private final ServerConnection client;
    private JogoVelhaClientMessage request;

    public ServerListener(ServerConnection client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            if (this.client.isConnected() && this.client.isInputStreamNotEmpty()) {
                this.request = (JogoVelhaClientMessage) this.client.readMessage();
            }
        } catch (IOException ex) {
            try {
                this.client.closeConnection();
            } catch (IOException ex1) {
                Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public JogoVelhaClientMessage getRequest() {
        return this.request;
    }
}
