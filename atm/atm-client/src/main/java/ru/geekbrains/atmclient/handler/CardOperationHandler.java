package ru.geekbrains.atmclient.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.model.Bill;
import ru.geekbrains.atmclient.service.HardwareService;
import ru.geekbrains.atmclient.service.ServerService;
import ru.geekbrains.atmclient.service.SessionService;

import java.util.List;

@Component
public class CardOperationHandler {
    private final HardwareService hardwareService;
    private final ServerService serverService;
    private final SessionService sessionService;

    @Autowired
    public CardOperationHandler(HardwareService hardwareService,
                                @Qualifier("billsCachedServerService") ServerService serverService,
                                SessionService sessionService) {
        this.hardwareService = hardwareService;
        this.serverService = serverService;
        this.sessionService = sessionService;
    }

    public void cashInPrepare() {
        hardwareService.cashInPrepare();
    }

    public int cashIn() {
        int amount = hardwareService.cashIn();
        String token = sessionService.getToken();
        serverService.cashIn(token, amount);
        return amount;
    }

    public boolean cashOut(int amount) {
        String token = sessionService.getToken();
        if (serverService.getBalance(token) >= amount) {
            hardwareService.cashOut(amount);
            serverService.cashOut(token, amount);
            return true;
        }
        return false;
    }

    public int getBalance() {
        String token = sessionService.getToken();
        return serverService.getBalance(token);
    }

    public List<Bill> getBillsList() {
        String token = sessionService.getToken();
        return serverService.getBillsList(token);
    }

    public boolean payBill(Bill bill) {
        String token = sessionService.getToken();
        if (serverService.getBalance(token) >= bill.getAmount()) {
            int amountIn = hardwareService.cashIn();
            if (amountIn == bill.getAmount()) {
                serverService.payBill(token, bill);
                return true;
            }
        }
        return false;
    }
}
