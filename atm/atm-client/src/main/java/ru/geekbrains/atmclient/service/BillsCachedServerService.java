package ru.geekbrains.atmclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.geekbrains.atmclient.model.Bill;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Primary
@Service("billsCachedServerService")
public class BillsCachedServerService implements ServerService {

    private final ServerService serverService;

    private List<Bill> cachedBills;
    private LocalDateTime cachedTime;
    @Value("${app.cache-expiring.bill.value}")
    private int billCacheExpiringTimeValue;
    @Value("${app.cache-expiring.bill.unit}")
    private String billCacheExpiringTimeUnit;

    @Autowired
    public BillsCachedServerService(@Qualifier("serverServiceImpl") ServerService serverService) {
        this.serverService = serverService;
    }

    @Override
    public String auth(String cardNumber, String pinCode) {
        return serverService.auth(cardNumber, pinCode);
    }

    @Override
    public String adminAuth(String authToken, String pinCode) {
        return serverService.adminAuth(authToken, pinCode);
    }

    @Override
    public boolean endSession(String authToken) {
        return serverService.endSession(authToken);
    }

    @Override
    public int getBalance(String authToken) {
        return serverService.getBalance(authToken);
    }

    @Override
    public boolean cashIn(String authToken, int amount) {
        return serverService.cashIn(authToken, amount);
    }

    @Override
    public boolean cashOut(String authToken, int amount) {
        return serverService.cashOut(authToken, amount);
    }

    @Override
    public boolean payBill(String authToken, Bill bill) {
        return serverService.payBill(authToken, bill);
    }

    @Override
    public List<Bill> getBillsList(String authToken) {
        if (cachedTime == null || cachedBills == null
                || ChronoUnit.valueOf(billCacheExpiringTimeUnit).between(cachedTime, LocalDateTime.now()) >= billCacheExpiringTimeValue) {
            cachedBills = serverService.getBillsList(authToken);
            cachedTime = LocalDateTime.now();
        }
        return cachedBills;
    }
}
