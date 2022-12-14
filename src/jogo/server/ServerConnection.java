package jogo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {

    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
        this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
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
    
    public boolean isConnected() {
        return this.socket.isConnected();
    }
    
    public boolean isSocketNotEmpty() {
        return this.socket != null;
    }
    
    public boolean isInputStreamNotEmpty() {
        return this.input != null;
    }
    
    public boolean isOutputStreamNotEmpty() {
        return this.objectOutputStream != null;
    }
    
    public Object readMessage() throws IOException, ClassNotFoundException {
        return this.objectInputStream.readObject();
    }
                    
    public void closeConnection() throws IOException {
        this.objectInputStream.close();
        this.objectOutputStream.close();
        this.input.close();
        this.output.close();
        this.socket.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.closeConnection();
    }
}
