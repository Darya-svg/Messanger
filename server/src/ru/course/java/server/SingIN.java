package ru.course.java.server;



import java.io.IOException;

public class SingIN implements IMessage {

    @Override
    public String handleIncomingMessage(String log, String pas) throws IOException, ClassNotFoundException {

        WorkWithFile workWithFile = new WorkWithFile();
        if(workWithFile.searchInFile(log)) return log;
        else return "error";


    }
}
