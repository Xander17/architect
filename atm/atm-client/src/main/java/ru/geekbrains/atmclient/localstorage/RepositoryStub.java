package ru.geekbrains.atmclient.localstorage;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class RepositoryStub implements LocalRepository {

    @Override
    public List<String> getAllStats() {
        return Collections.emptyList();
    }
}
