package ru.geekbrains.atmclient.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.operation.CardOperationRequest;
import ru.geekbrains.atmclient.operation.CardOperationResponse;
import ru.geekbrains.atmclient.operation.CardOperationType;
import ru.geekbrains.atmclient.operation.command.*;

import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.atmclient.operation.CardOperationType.*;

@Component
public class CardOperationHandler {

    private final Map<CardOperationType, CardOperation> map;

    @Autowired
    public CardOperationHandler(CashInPrepareOperation cashInPrepareOperation,
                                CashInOperation cashInOperation,
                                CashOutOperation cashOutOperation,
                                GetBalanceOperation getBalanceOperation,
                                GetBillsListOperation getBillsListOperation,
                                PayBillOperation payBillOperation) {
        map = new HashMap<>();
        map.put(CASH_IN_PREPARE, cashInPrepareOperation);
        map.put(CASH_IN, cashInOperation);
        map.put(CASH_OUT, cashOutOperation);
        map.put(GET_BALANCE, getBalanceOperation);
        map.put(GET_BILLS_LIST, getBillsListOperation);
        map.put(PAY_BILL, payBillOperation);
    }

    public CardOperationResponse handle(CardOperationType type) {
        return handle(type, new CardOperationRequest());
    }

    public CardOperationResponse handle(CardOperationType type, CardOperationRequest request) {
        CardOperation cardOperation = map.get(type);
        if (cardOperation == null) {
            throw new UnsupportedOperationException();
        }
        return cardOperation.execute(request);
    }
}
