/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.server;

import java.io.IOException;
import java.util.List;
import jogo.lib.JogoVelhaServerMessage;

/**
 *
 * @author matheus
 */
public class JogoVelhaServerDispatcher {
    
    protected JogoVelhaServerClientsHandler clientsHandler;

    public JogoVelhaServerDispatcher(JogoVelhaServerClientsHandler clientsHandler ) {
        this.clientsHandler = clientsHandler;
    }
    
    public synchronized void dispatchMessageToAllClients(JogoVelhaServerMessage response) throws IOException {
        List<JogoVelhaServerConnection> clients = clientsHandler.getClients();
        for (JogoVelhaServerConnection cli : clients) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getObjectOutputStream().writeObject(response);
                cli.getObjectOutputStream().flush();
            }
        }
        System.out.println("Dispatch message to clients");
    }
    
}
