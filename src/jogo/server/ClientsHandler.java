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
public class ClientsHandler {

    protected final List<ServerConnection> clients;

    public ClientsHandler() {
        this.clients = new ArrayList<>();
    }

    public List getClients() {
        return this.clients;
    }

    public boolean isClientsNotFull() {
        return this.clients.size() < 2;
    }

    public synchronized void add(ServerConnection client) {
        this.clients.add(client);
    }

    public synchronized void remove(ServerConnection client) throws IOException {
        this.clients.remove(client);
        client.closeConnection();
    }

    public synchronized void removeAllClients() throws IOException {
        for (ServerConnection cli : this.clients) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                this.remove(cli);
            }
        }
    }

    public int getClientPosition(ServerConnection client) {
        return this.getClients().indexOf(client) + 1;
    }
}
