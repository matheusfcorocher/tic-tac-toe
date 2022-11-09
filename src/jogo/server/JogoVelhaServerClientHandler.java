package jogo.server;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
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
                if (this.client.getSocket().isConnected() && this.client.getInput() != null) {
                    message = this.client.getInput().readLine();
                } else {
                    break;
                }

                if (message == null || message.equals("")) {
                    break;
                }

                int p = server.clientsHandler.getClients().indexOf(this.client);

                System.out.println(String.valueOf(p));
                if (p == -1) {
                    break;
                }

                int q = Integer.parseInt(message);

                JogoVelhaServerMessage response = server.game.execute(p, q);
                this.dispatcher.dispatchMessageToAllClients(response);
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
