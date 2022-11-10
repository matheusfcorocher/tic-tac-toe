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

    protected final List<JogoVelhaServerConnection> clients;

    public JogoVelhaServerClientsHandler() {
        this.clients = new ArrayList<>();
    }

    public List getClients() {
        return this.clients;
    }

    public boolean isClientsNotFull() {
        return clients.size() <= 2;
    }

    public synchronized void add(JogoVelhaServerConnection client) {
        this.clients.add(client);
    }

    public synchronized void remove(JogoVelhaServerConnection client) throws IOException {
        this.clients.remove(client);
        client.closeConnection();
    }

    public synchronized void removeAllClients() throws IOException {
        for (JogoVelhaServerConnection cli : this.clients) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                this.remove(cli);
            }
        }
    }
    
    public int getClientPosition(JogoVelhaServerConnection client) {
        return this.getClients().indexOf(client) + 1;
    }
}
