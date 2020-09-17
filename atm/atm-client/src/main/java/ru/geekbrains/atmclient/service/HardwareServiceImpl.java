package ru.geekbrains.atmclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HardwareServiceImpl implements HardwareService {

    public String getCardNumber() {
        log.info("Запрошен номер карты");
        // code
        return "0000000000000000";
    }

    public boolean cardOut() {
        log.info("Карта удаляется из банкомата");
        // code
        return true;
    }

    @Override
    public void cashInPrepare() {
        log.info("Открытие лотка для денег");
        // code
    }

    public int cashIn() {
        log.info("Банкноты вложены в банкомат");
        int sum = 0;
        // code
        log.info("Банкноты посчитанны, сумма - {}", sum);
        return sum;
    }

    public boolean cashOut(int amount) {
        log.info("Выдается сумма - {}", amount);
        // code
        return true;
    }

    public String getAdminAuthToken() {
        log.info("Считывает внешний токен");
        //code
        return "token";
    }
}
