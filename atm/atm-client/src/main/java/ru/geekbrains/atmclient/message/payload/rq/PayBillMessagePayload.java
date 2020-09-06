package ru.geekbrains.atmclient.message.payload.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.atmclient.message.payload.MessagePayload;
import ru.geekbrains.atmclient.model.Bill;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayBillMessagePayload implements MessagePayload {
    private Bill bill;
}
