package ru.course.java.server;

import forms.CheckIN;
import ru.course.java.client.User;
import ru.course.java.connection.TCPConnection;
import ru.course.java.connection.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer implements TCPConnectionListener {

    private static String login;


    public static void main(String args[]) throws IOException {

        new ChatServer();

    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();
    private Factory fac;


    private ChatServer() throws IOException {
        System.out.println("Server is running...");
        try(ServerSocket serverSocket = new ServerSocket(8189)){
            while(true){
                try {
                    new TCPConnection(serverSocket.accept(), this);
                }catch (IOException e){
                    System.out.println(e);
                }

            }

                } catch (IOException e){
            throw new RuntimeException();

        }
    }

    @Override
    public synchronized void readyConnection(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendMessage("Client connected : " + tcpConnection.toString());

    }

    @Override
    public synchronized void getString(TCPConnection tcpConnection, String str) {

        sendMessage(str);

    }

    @Override
    public synchronized void disconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendMessage("Client disconnected : " + tcpConnection.toString());

    }

    @Override
    public synchronized void exceptionConnect(TCPConnection tcpConnection, Exception e) {
        System.out.println("Exception :"+ e);
    }

    private void sendMessage(String str){
        System.out.println(str);
        int countConnections = connections.size();
        for(int i = 0;i<countConnections;i++){
            connections.get(i).sendString(str);
        }
    }

    public static void initImpl(String key,String log,String pas) throws IOException, ClassNotFoundException {
        Factory fac = new Factory();
        IMessage impl = fac.getImplm(key);
        login = impl.handleIncomingMessage(log,pas);
    }

    public static String getUserLogin(){
        return login ;
    }

}
