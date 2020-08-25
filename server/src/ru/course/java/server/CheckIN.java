package ru.course.java.server;

import ru.course.java.client.User;


import java.io.IOException;

public class CheckIN implements IMessage {

    @Override
    public String handleIncomingMessage(String log,String pas) throws IOException {
        // create
        User user = new User(log,pas);

        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.serializator(user);

        return user.getLogin();


    }
}
