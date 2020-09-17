package ru.geekbrains.atmclient.message;

import ru.geekbrains.atmclient.message.payload.MessagePayload;

public class ServerMessageBuilder {

    private final ServerMessage serverMessage;

    public ServerMessageBuilder(ServerMessage serverMessage) {
        this.serverMessage = serverMessage;
    }

    public static ServerMessageBuilder builder() {
        return new ServerMessageBuilder(new ServerMessage());
    }

    public ServerMessageBuilder authToken(String authToken) {
        serverMessage.setAuthToken(authToken);
        return this;
    }

    public ServerMessageBuilder operation(OperationType operation) {
        serverMessage.setOperation(operation);
        return this;
    }

    public ServerMessageBuilder payload(MessagePayload payload) {
        serverMessage.setPayload(payload);
        return this;
    }

    public ServerMessage build() {
        return serverMessage;
    }
}
