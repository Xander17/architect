package ru.geekbrains.atmclient.operation.command;

import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;

@Component
public class CashInPrepareOperation implements CardOperation {
    @Override
    public CardOperationResponse execute(CardOperationRequest request) {
        return new CardOperationResponse(true);
    }
}
