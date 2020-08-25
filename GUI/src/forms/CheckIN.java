package forms;

import javax.swing.*;

import ru.course.java.client.ClientWindow;
import ru.course.java.client.User;
import ru.course.java.connection.TCPConnection;
import ru.course.java.connection.TCPConnectionListener;
import ru.course.java.server.ChatServer;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;



public class CheckIN   {

    private JFrame frame;
    private JTextField loginField;
    private JTextField passwordField;
    private JLabel errorLabel;

    private TCPConnection connection;
    private TCPConnectionListener eventListener;
    private String login;


    public CheckIN() {

    }

    public CheckIN(TCPConnection tcpConnection) throws IOException {
        initialize(tcpConnection);
    }


    private void initialize(TCPConnection tcpConnection) {
        this.connection = tcpConnection;



        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setBounds(33, 82, 75, 19);
        frame.getContentPane().add(loginLabel);

        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(33, 128, 75, 19);
        frame.getContentPane().add(passwordLabel);

        loginField = new JTextField();
        loginField.setBounds(180, 81, 86, 20);
        frame.getContentPane().add(loginField);
        loginField.setColumns(10);

        passwordField = new JTextField();
        passwordField.setBounds(180, 127, 86, 20);
        frame.getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JButton checkInButton = new JButton("Регистрация");

        checkInButton.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                try {

                    connection.sendString("CheckIN",loginField.getText(),passwordField.getText());
                    login = ChatServer.getUserLogin();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new ClientWindow(connection,login);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }

                        }
                    });
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }

                /* WorkWithFile wwf = new WorkWithFile();

                try {
                    if(wwf.searchInFile(loginField.getText())) {
                        errorLabel.setText("User is already used");
                        passwordField.setText("");

                    }else {
                        if(wwf.serializator(new User(loginField.getText(),passwordField.getText()))) {
                            System.out.print("User successfully added");
                        }
                    }
                } catch (ClassNotFoundException | IOException e1) {


                    e1.printStackTrace();
                }*/ //отправить данные на сервер

                //ключевое слов,данные





            }});
        checkInButton.setBounds(106, 186, 146, 23);
        frame.getContentPane().add(checkInButton);

        errorLabel = new JLabel("");
        errorLabel.setBounds(180, 99, 46, 14);
        frame.getContentPane().add(errorLabel);


    }


}

