/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.cliente;

import java.io.IOException;
import jogo.lib.ClientMessage;

/**
 * this class has the purpose to handle all user inputs
 * @author matheus
 */
public class JogoVelhaClientHandler {
    
    private final ClientConnection clientConnection;
    
    public JogoVelhaClientHandler(ClientConnection clientConnection) throws IOException {
        this.clientConnection = clientConnection;
    }
    
    public void sendMessage(ClientMessage message) {
        this.clientConnection.sendObject(message);
    }
    
}
