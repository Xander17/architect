package ru.geekbrains.atmclient.operation.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;
import ru.geekbrains.atmclient.service.ServerService;
import ru.geekbrains.atmclient.service.SessionService;

@Component
@AllArgsConstructor
public class GetBalanceOperation implements CardOperation {

    private final ServerService serverService;
    private final SessionService sessionService;

    @Override
    public CardOperationResponse execute(CardOperationRequest request) {
        String token = sessionService.getToken();
        int balance = serverService.getBalance(token);
        return new CardOperationResponse(true, balance);
    }
}
