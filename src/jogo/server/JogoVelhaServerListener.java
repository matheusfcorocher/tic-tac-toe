package jogo.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaServerListener implements Runnable {

    private final JogoVelhaServerConnection client;
    private String request;

    public JogoVelhaServerListener(JogoVelhaServerConnection client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            if (this.client.isConnected() && this.client.isInputStreamNotEmpty()) {
                this.request = this.client.readMessage();
            }
        } catch (IOException ex) {
            Logger.getLogger(JogoVelhaServerListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRequest() {
        return this.request;
    }
}
