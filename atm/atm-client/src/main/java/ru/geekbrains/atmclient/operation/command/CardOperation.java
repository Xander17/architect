package ru.geekbrains.atmclient.operation.command;

import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;

public interface CardOperation {
    CardOperationResponse execute(CardOperationRequest request);
}
