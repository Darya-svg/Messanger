package ru.course.java.server;

import ru.course.java.client.User;

import java.io.IOException;

public interface IMessage {

    public String handleIncomingMessage(String log, String pas) throws IOException, ClassNotFoundException;
}
