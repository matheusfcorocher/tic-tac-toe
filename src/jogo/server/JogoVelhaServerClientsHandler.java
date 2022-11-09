package jogo.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jogo.lib.ClientMaximumLimitException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author matheus
 */
public class JogoVelhaServerClientsHandler {
    
    protected final List<JogoVelhaServerConnection> clientes;

    public JogoVelhaServerClientsHandler() {
        this.clientes = new ArrayList<>();
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
}
