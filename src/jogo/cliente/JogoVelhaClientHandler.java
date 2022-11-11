/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.cliente;

import java.io.IOException;
import jogo.lib.JogoVelhaClientMessage;

/**
 * this class has the purpose to handle all user inputs
 * @author matheus
 */
public class JogoVelhaClientHandler {
    
    private final JogoVelhaClientConnection clientConnection;
    
    public JogoVelhaClientHandler(JogoVelhaClientConnection clientConnection) throws IOException {
        this.clientConnection = clientConnection;
    }
    
    public void sendMessage(JogoVelhaClientMessage message) {
        this.clientConnection.sendObject(message);
    }
    
}
