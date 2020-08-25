package forms;

import ru.course.java.connection.TCPConnection;
import ru.course.java.connection.TCPConnectionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Autorization1 implements TCPConnectionListener {

    private static final String IP_ADDRESS = "192.168.56.1";
    private static final int PORT = 8189;
  //  private static final int WIDTH = 600;
  //  private static final int HEIGHT = 400;
    private TCPConnection connection;


    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Autorization1();
            }
        });

    }
    private JFrame frame;
    private JTextField textField;

    public Autorization1() {
        initialize();
    }


    private void initialize() {
        try {
            // создали соединение
            connection = new TCPConnection(this,IP_ADDRESS,PORT);
        } catch (IOException e) {
            System.out.println("Connection exception:"+e);
        }
        frame = new JFrame();
        frame.setVisible(true);

        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        textField = new JTextField();
        textField.setText("Добро пожаловать!");
        textField.setBounds(136, 43, 109, 26);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Войти");
        btnNewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SignIN(connection);
                    }
                });
            }
        });
        btnNewButton.setBounds(65, 131, 89, 23);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Регистрация");
        btnNewButton_1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new CheckIN(connection);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });


            }

        });
        btnNewButton_1.setBounds(228, 131, 97, 23);
        frame.getContentPane().add(btnNewButton_1);
    }
    @Override
    public void readyConnection(TCPConnection tcpConnection) {

    }

    @Override
    public void getString(TCPConnection tcpConnection, String str) {

    }

    @Override
    public void disconnect(TCPConnection tcpConnection) {

    }

    @Override
    public void exceptionConnect(TCPConnection tcpConnection, Exception e) {

    }
}
