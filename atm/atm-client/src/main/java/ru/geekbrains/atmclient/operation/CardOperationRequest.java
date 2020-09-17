package ru.geekbrains.atmclient.operation;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.atmclient.model.Bill;

@Data
@Builder
@NoArgsConstructor
public class CardOperationRequest {
    private Bill bill;
    private int amount;
}
