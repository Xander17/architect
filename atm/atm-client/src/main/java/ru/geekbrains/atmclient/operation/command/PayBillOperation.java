package ru.geekbrains.atmclient.operation.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.model.Bill;
import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;
import ru.geekbrains.atmclient.service.HardwareService;
import ru.geekbrains.atmclient.service.ServerService;
import ru.geekbrains.atmclient.service.SessionService;

@Component
@AllArgsConstructor
public class PayBillOperation implements CardOperation {

    private final HardwareService hardwareService;
    private final ServerService serverService;
    private final SessionService sessionService;

    @Override
    public CardOperationResponse execute(CardOperationRequest request) {
        String token = sessionService.getToken();
        Bill bill = request.getBill();
        if (serverService.getBalance(token) >= bill.getAmount()) {
            int amountIn = hardwareService.cashIn();
            if (amountIn == bill.getAmount()) {
                serverService.payBill(token, bill);
                return new CardOperationResponse(true);
            }
        }
        return new CardOperationResponse(false);
    }
}
