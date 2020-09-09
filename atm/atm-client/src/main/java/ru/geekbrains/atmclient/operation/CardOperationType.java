package ru.geekbrains.atmclient.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardOperationType {
    CASH_IN_PREPARE,
    CASH_IN,
    CASH_OUT,
    GET_BALANCE,
    GET_BILLS_LIST,
    PAY_BILL;
}
