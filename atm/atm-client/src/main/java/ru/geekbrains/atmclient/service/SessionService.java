package ru.geekbrains.atmclient.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Data
public class SessionService {

    private String token;

    public boolean isAuthorized() {
        return !StringUtils.isEmpty(token);
    }

    public boolean isNotAuthorized() {
        return StringUtils.isEmpty(token);
    }
}
