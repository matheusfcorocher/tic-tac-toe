/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author matheus
 */
public class JogoVelhaServerHandler {
    
    private ServerSocket server;
        
    public void create(int port) throws IOException {
        this.server = new ServerSocket(port);
    }

    public ServerSocket getServer() {
        return this.server;
    }
    
    public Socket acceptClient() throws IOException {
        return this.server.accept();
    }
    
    public void close() throws IOException {
        this.server.close();
    }
}
