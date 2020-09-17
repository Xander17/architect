package ru.geekbrains.atmclient.service;

public interface HardwareService {

    String getCardNumber();

    boolean cardOut();

    int cashIn();

    boolean cashOut(int amount);

    String getAdminAuthToken();

    void cashInPrepare();
}
