package ru.geekbrains.atmclient.message.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.atmclient.message.payload.MessagePayload;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserMessagePayload implements MessagePayload {
    private String cardNumber;
    private String pinCode;
}
