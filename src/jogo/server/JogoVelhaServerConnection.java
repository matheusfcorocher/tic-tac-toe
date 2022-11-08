package jogo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class JogoVelhaServerConnection {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public JogoVelhaServerConnection(Socket socket) {
        this.socket = socket;
        try {
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(this.socket.getOutputStream(), true);
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.input.close();
        this.output.close();
        this.socket.close();
    }

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedReader getInput() {
        return this.input;
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
}
