package ru.geekbrains.atmclient.message.connection;

import ru.geekbrains.atmclient.message.ServerMessage;

public interface ServerConnection {

    <T> T sendMessageAndGetResponse(ServerMessage message, Class<T> responseType);
}
