/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jogo.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.lib.JogoVelhaClientMessage;

/**
 *
 * @author matheus
 */
public class JogoVelhaClientConnection {

    private Socket socket;
    private PrintWriter output;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public JogoVelhaClientConnection(String serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.socket.setKeepAlive(true);
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
        this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
    }
    
    public Socket getSocket() {
        return this.socket;
    }

    public PrintWriter getOutput() {
        return this.output;
    }

    public ObjectInputStream getObjectInputStream() {
        return this.objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return this.objectOutputStream;
    }

    public void writeMessage(String outMessage) {
        this.output.println(outMessage);
    }

    public void sendObject(JogoVelhaClientMessage message) {
        try {
            this.objectOutputStream.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(JogoVelhaClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isConnected() {
        return this.socket.isConnected();
    }
    
    public boolean isInputStreamNotEmpty() {
        return this.objectInputStream != null;
    }
      
    public Object readMessage() throws IOException, ClassNotFoundException {
        return this.objectInputStream.readObject();
    }

    @Override
    protected void finalize() throws Throwable {
        this.closeConnection();
    }

    public void closeConnection() throws IOException {
        this.objectOutputStream.close();
        this.objectInputStream.close();
        this.output.close();
        this.socket.close();
    }
}
