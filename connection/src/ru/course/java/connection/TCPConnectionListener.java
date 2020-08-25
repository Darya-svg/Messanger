package ru.course.java.connection;

import ru.course.java.client.User;

public interface TCPConnectionListener {

    //готово
    void readyConnection(TCPConnection tcpConnection);

    // принято входящее сообщение
    void getString(TCPConnection tcpConnection,String str);

    // дисконнект
    void disconnect(TCPConnection tcpConnection);

    // исключение
    void exceptionConnect(TCPConnection tcpConnection,Exception e);

}
