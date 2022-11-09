package jogo.server;

import java.io.IOException;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaServerClientHandler extends Thread {

    private final JogoVelhaServerConnection client;
    private final JogoVelhaServer server;
    private final JogoVelhaServerDispatcher dispatcher;

    public JogoVelhaServerClientHandler(JogoVelhaServerConnection client, JogoVelhaServer server) throws IOException {
        this.client = client;
        this.server = server;
        this.dispatcher = new JogoVelhaServerDispatcher(this.server.clientsHandler);
    }

    private void close() throws IOException {
        this.server.clientsHandler.remove(this.client);
    }

    @Override
    public void run() {
        try {
            String message;
            while (server.isRunning) {
                if (this.client.isConnected() && this.client.isInputStreamNotEmpty()) {
                    message = this.client.readMessage();
                  
                    int p = server.clientsHandler.getClients().indexOf(this.client);
                    int q = Integer.parseInt(message);
                    
                    JogoVelhaServerMessage response = server.game.execute(p, q);
                    this.dispatcher.dispatchMessageToAllClients(response);
                }
            }
            close();
        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
    }
}
