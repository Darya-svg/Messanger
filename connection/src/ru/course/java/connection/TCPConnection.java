package ru.course.java.connection;

import ru.course.java.server.ChatServer;

import java.io.*;
import java.net.Socket;

// одно TCP соединение
public class TCPConnection {

    private final Socket socket;
    private final Thread inThread;
    private final BufferedReader input;
    private final BufferedWriter output;
    private TCPConnectionListener eventListener;

    public TCPConnection(TCPConnectionListener eventListener, String ip, int port) throws IOException {
        this(new Socket(ip,port), eventListener);
    }

    public TCPConnection(Socket socket, TCPConnectionListener eventListener) throws IOException {
        this.socket = socket;
        this.eventListener = eventListener;

        // входящий поток (можно указать кодировку)
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //исходящий поток
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //анонимный класс (слушаем входящее соединение)
        inThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    eventListener.readyConnection(TCPConnection.this);

                    while (!inThread.isInterrupted()) {
                        //получили строку
                        String str = input.readLine();
                      //  передали ее либо серверу либо клиенту
                        eventListener.getString(TCPConnection.this, str);
                    }
                } catch (IOException e) {
                    eventListener.exceptionConnect(TCPConnection.this, e);
                } finally {
                   eventListener.disconnect(TCPConnection.this);
                }


            }
        });
        inThread.start();
    }
    public synchronized void sendString(String mes,String log,String pas) throws IOException, ClassNotFoundException {

        ChatServer.initImpl(mes,log,pas);
      /*  if (mes.equals("CheckIN")){

            ChatServer.initImpl(mes,log,pas);
        } else if(mes.equals("SingIN")){
            ChatServer.initImpl();
        }*/


    }

    public synchronized void sendString(String mes){
        try {
            output.write(mes + "\n");
            output.flush();
        } catch (IOException e) {
            eventListener.exceptionConnect(TCPConnection.this, e);
            disconnectConnect();
        }

    }

    public synchronized void disconnectConnect(){
        inThread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.exceptionConnect(TCPConnection.this, e);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }
}
