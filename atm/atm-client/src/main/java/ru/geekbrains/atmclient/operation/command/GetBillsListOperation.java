package ru.geekbrains.atmclient.operation.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.model.Bill;
import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;
import ru.geekbrains.atmclient.service.ServerService;
import ru.geekbrains.atmclient.service.SessionService;

import java.util.List;

@Component
@AllArgsConstructor
public class GetBillsListOperation implements CardOperation {

    private final ServerService serverService;
    private final SessionService sessionService;

    @Override
    public CardOperationResponse execute(CardOperationRequest request) {
        String token = sessionService.getToken();
        List<Bill> billsList = serverService.getBillsList(token);
        return new CardOperationResponse(billsList);
    }
}
