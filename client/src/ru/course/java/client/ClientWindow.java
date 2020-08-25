package ru.course.java.client;

import ru.course.java.connection.TCPConnection;
import ru.course.java.connection.TCPConnectionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {


        private static final int WIDTH = 600;
        private static final int HEIGHT = 400;
    private static final String IP_ADDRESS = "192.168.56.1";
    private static final int PORT = 8189;
        private TCPConnection connection;
 //   private ActionListener actionListener;


  /*  public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });

    }*/

    private JTextArea log = new JTextArea();
    private JTextField fieldNickname;
    private JTextField fieldInput = new JTextField();


    public ClientWindow(TCPConnection tcpConnection,String login) throws IOException {

         this.fieldNickname = new JTextField(login);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        log.setEditable(false);
        log.setLineWrap(true);
        add(log, BorderLayout.CENTER);

        fieldInput.addActionListener(this);
        add(fieldNickname,BorderLayout.NORTH);
        add(fieldInput,BorderLayout.SOUTH);

        setVisible(true);

        connection = new TCPConnection(this,IP_ADDRESS,PORT);
       /* this.connection = tcpConnection;
System.out.print(connection.toString());*/
    }

    // нажатие enter
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if(msg.equals("")) return;
        fieldInput.setText(null);
        connection.sendString(fieldNickname.getText()+" "+ msg);

    }

    @Override
    public void readyConnection(TCPConnection tcpConnection) {
        printMessage("Connection ready...");

    }

    @Override
    public void getString(TCPConnection tcpConnection, String str) {
printMessage(str);
    }

    @Override
    public void disconnect(TCPConnection tcpConnection) {
        printMessage("Connection close...");
    }

    @Override
    public void exceptionConnect(TCPConnection tcpConnection, Exception e) {
        printMessage("Connection exception:"+e);

    }

    private synchronized void printMessage(String str){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(str + "\n");
              //  log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}
