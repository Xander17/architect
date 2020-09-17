package ru.geekbrains.atmclient.service;

import ru.geekbrains.atmclient.model.Bill;

import java.util.List;

public interface ServerService {

    String auth(String cardNumber, String pinCode);

    String adminAuth(String authToken, String pinCode);

    boolean endSession(String authToken);

    int getBalance(String authToken);

    boolean cashIn(String authToken, int amount);

    boolean cashOut(String authToken, int amount);

    boolean payBill(String authToken, Bill bill);

    List<Bill> getBillsList(String authToken);
}
