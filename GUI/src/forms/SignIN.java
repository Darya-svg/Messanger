package forms;

import ru.course.java.client.ClientWindow;
import ru.course.java.connection.TCPConnection;
import ru.course.java.server.ChatServer;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SignIN {

    private JFrame frame;
    private JTextField loginField;
    private JTextField passwordField;
    private TCPConnection connection;
    private String login;


    public SignIN(TCPConnection tcpConnection) {
        this.connection = tcpConnection;
        initialize();
    }


    private void initialize() {
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

        JLabel errorLabel = new JLabel("New label");
        errorLabel.setBounds(180, 102, 86, 14);

        frame.getContentPane().add(errorLabel);

        passwordField = new JTextField();
        passwordField.setBounds(180, 127, 86, 20);
        frame.getContentPane().add(passwordField);
        passwordField.setColumns(10);

        JButton signInButton = new JButton("Войти");
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    connection.sendString("SignIN",loginField.getText(),passwordField.getText());

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

                      //  new ClientWindow(loginField.getText());
                    }else {
                        errorLabel.setText("user not found");
                        passwordField.setText("");
                    }
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }*/
            }
        });
        signInButton.setBounds(106, 186, 146, 23);
        frame.getContentPane().add(signInButton);


    }
}

