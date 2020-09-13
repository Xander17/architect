package ru.geekbrains.atmclient.model;

import lombok.Data;

@Data
public abstract class Identify<T> {
    private T id;
}
