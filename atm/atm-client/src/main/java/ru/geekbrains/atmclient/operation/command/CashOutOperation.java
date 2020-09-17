package ru.geekbrains.atmclient.operation.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;
import ru.geekbrains.atmclient.service.HardwareService;
import ru.geekbrains.atmclient.service.ServerService;
import ru.geekbrains.atmclient.service.SessionService;

@Component
@AllArgsConstructor
public class CashOutOperation implements CardOperation {

    private final HardwareService hardwareService;
    private final ServerService serverService;
    private final SessionService sessionService;

    @Override
    public CardOperationResponse execute(CardOperationRequest request) {
        String token = sessionService.getToken();
        int amount = request.getAmount();
        if (serverService.getBalance(token) >= amount) {
            hardwareService.cashOut(amount);
            serverService.cashOut(token, amount);
            return new CardOperationResponse(true, null);
        }
        return new CardOperationResponse(false);
    }
}
