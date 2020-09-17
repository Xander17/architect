package ru.geekbrains.atmclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.atmclient.message.ServerMessageBuilder;
import ru.geekbrains.atmclient.message.connection.RestConnection;
import ru.geekbrains.atmclient.message.payload.rq.AuthAdminMessagePayload;
import ru.geekbrains.atmclient.message.payload.rq.AuthUserMessagePayload;
import ru.geekbrains.atmclient.message.payload.rq.CashAmountMessagePayload;
import ru.geekbrains.atmclient.message.payload.rq.PayBillMessagePayload;
import ru.geekbrains.atmclient.message.payload.rs.BillsListPayloadResponse;
import ru.geekbrains.atmclient.model.Bill;

import java.util.List;

import static ru.geekbrains.atmclient.message.OperationType.*;

@Service("serverServiceImpl")
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final RestConnection connection;

    public String auth(String cardNumber, String pinCode) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .operation(AUTH)
                        .payload(new AuthUserMessagePayload(cardNumber, pinCode))
                        .build(),
                String.class
        );
    }

    public boolean endSession(String authToken) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(END_SESSION)
                        .build(),
                Boolean.class
        );
    }

    public String adminAuth(String authToken, String pinCode) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(ADMIN_AUTH)
                        .payload(new AuthAdminMessagePayload(pinCode))
                        .build(),
                String.class
        );
    }

    public boolean cashIn(String authToken, int amount) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(CASH_IN)
                        .payload(new CashAmountMessagePayload(amount))
                        .build(),
                Boolean.class
        );
    }

    public boolean cashOut(String authToken, int amount) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(CASH_OUT)
                        .payload(new CashAmountMessagePayload(amount))
                        .build(),
                Boolean.class
        );
    }

    public int getBalance(String authToken) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(BALANCE)
                        .build(),
                Integer.class
        );
    }

    public boolean payBill(String authToken, Bill bill) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(PAY_BILL)
                        .payload(new PayBillMessagePayload(bill))
                        .build(),
                Boolean.class
        );
    }

    @Override
    public List<Bill> getBillsList(String authToken) {
        return connection.sendMessageAndGetResponse(
                ServerMessageBuilder.builder()
                        .authToken(authToken)
                        .operation(BILLS_LIST)
                        .build(),
                BillsListPayloadResponse.class)
                .getBills();
    }
}
