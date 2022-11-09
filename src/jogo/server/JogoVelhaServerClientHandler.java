package jogo.server;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaServerClientHandler extends Thread {

    private final JogoVelhaServerConnection client;
    private final JogoVelhaServer server;

    public JogoVelhaServerClientHandler(JogoVelhaServerConnection client, JogoVelhaServer server) throws IOException {
        this.client = client;
        this.server = server;
    }

    private void close() throws IOException {
        this.server.clientsHandler.remove(this.client);
    }

    public synchronized void messageDispatcher(JogoVelhaServerMessage response) throws IOException {
        List<JogoVelhaServerConnection> clients = this.server.clientsHandler.getClients();
        for (JogoVelhaServerConnection cli : clients) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getObjectOutputStream().writeObject(response);
                cli.getObjectOutputStream().flush();
            }
        }
        System.out.println("Dispatch message to clients");
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
                this.messageDispatcher(response);
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
