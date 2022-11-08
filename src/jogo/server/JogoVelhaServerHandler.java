package jogo.server;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaServerHandler extends Thread {

    private final JogoVelhaServerConnection cliente;
    private final JogoVelhaServer caller;

    public JogoVelhaServerHandler(JogoVelhaServerConnection cliente, JogoVelhaServer caller) throws IOException {
        this.cliente = cliente;
        this.caller = caller;
    }

    @Override
    protected void finalize() throws Throwable {
        encerrar();
    }

    private void encerrar() {
        this.caller.removerCliente(this.cliente);
    }

    public synchronized void messageDispatcher(JogoVelhaServerMessage response) throws IOException {
        List<JogoVelhaServerConnection> clientes = this.caller.getClientes();
        for (JogoVelhaServerConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getObjectOutputStream().writeObject(response);
                cli.getObjectOutputStream().flush();
                System.out.println("Dispatch message to client");
            }
        }
    }

    @Override
    public void run() {
        String message;
        while (caller.isRunning) {
            try {
                if (this.cliente.getSocket().isConnected() && this.cliente.getInput() != null) {
                    message = this.cliente.getInput().readLine();
                } else {
                    break;
                }

                if (message == null || message.equals("")) {
                    break;
                }

                int p = caller.clientes.indexOf(this.cliente);

                System.out.println(String.valueOf(p));
                if (p == -1) {
                    break;
                }

                int q = Integer.parseInt(message);
                
                JogoVelhaServerMessage response = caller.game.execute(p, q);
                this.messageDispatcher(response);
            } catch (IOException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
        encerrar();
    }
}
