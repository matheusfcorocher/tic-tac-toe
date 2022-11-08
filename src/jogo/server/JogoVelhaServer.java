package jogo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import jogo.lib.ClientMaximumLimitException;

public class JogoVelhaServer extends Thread {

    private final ServerSocket server;
    protected final List<JogoVelhaServerConnection> clientes;
    protected final JogoVelha game;
    protected boolean isRunning;

    public JogoVelhaServer(int porta) throws IOException {
        this.game = new JogoVelha(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        this.server = new ServerSocket(porta);
        this.clientes = new ArrayList<>();
        this.isRunning = true;
        System.out.println("O servidor do Jogo da Velha está executando na porta: " + server.getLocalPort());
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                Socket socket = this.server.accept();
                JogoVelhaServerConnection cliente = new JogoVelhaServerConnection(socket);
                this.novoCliente(cliente);
                System.out.println("Cliente Aceito");
                (new JogoVelhaServerHandler(cliente, this)).start();
            } catch (IOException ex) {
                if (ex.getMessage().contentEquals("Socket closed")) {

                } else {
                    System.out.println("Quando o servidor estava executando, foi encontrado o seguinte erro: " + ex.getMessage());
                }
            }
        }
    }

    public List getClientes() {
        return this.clientes;
    }

    public synchronized void novoCliente(JogoVelhaServerConnection cliente) throws IOException {
        if (this.clientes.size() <= 2) {
            this.clientes.add(cliente);
        } else {
            ClientMaximumLimitException error = new ClientMaximumLimitException();
            cliente.getObjectOutputStream().writeObject(error);
        }
    }

    public synchronized void removerCliente(JogoVelhaServerConnection cliente) {
        this.clientes.remove(cliente);
        try {
            cliente.getInput().close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        cliente.getOutput().close();
        try {
            cliente.getSocket().close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized void removeAllClients() {
        for (JogoVelhaServerConnection cli : this.clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                this.removerCliente(cli);
            }
        }
    }

    public synchronized void finish() throws Throwable {
        this.finalize();
    }

    @Override
    protected synchronized void finalize() throws Throwable {
        try {
            this.isRunning = false;
            this.server.close();
            System.out.println("O servidor do Jogo da Velha parou de executar.");
        } finally {
            super.finalize();
        }
    }
}
