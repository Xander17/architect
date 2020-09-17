package ru.geekbrains.atmclient.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.atmclient.message.payload.MessagePayload;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerMessage {
    private String authToken;
    private OperationType operation;
    private MessagePayload payload;
}
