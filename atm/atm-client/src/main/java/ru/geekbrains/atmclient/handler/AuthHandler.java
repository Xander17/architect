package ru.geekbrains.atmclient.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.exception.UserNotAuthorizedException;
import ru.geekbrains.atmclient.service.HardwareService;
import ru.geekbrains.atmclient.service.ServerService;
import ru.geekbrains.atmclient.service.SessionService;

@Component
public class AuthHandler {

    private final HardwareService hardwareService;
    private final ServerService serverService;
    private final SessionService sessionService;

    public AuthHandler(HardwareService hardwareService,
                       @Qualifier("serverServiceImpl") ServerService serverService,
                       SessionService sessionService) {
        this.hardwareService = hardwareService;
        this.serverService = serverService;
        this.sessionService = sessionService;
    }

    public void authByCard(String pinCode) {
        String cardNumber = hardwareService.getCardNumber();
        String token = serverService.auth(cardNumber, pinCode);
        sessionService.setToken(token);
    }

    public void authByToken(String pinCode) {
        String authToken = hardwareService.getAdminAuthToken();
        String token = serverService.adminAuth(authToken, pinCode);
        sessionService.setToken(token);
    }

    public void endCardSession() {
        hardwareService.cardOut();
        String token = sessionService.getToken();
        serverService.endSession(token);
    }

    public void endTokenSession() {
        String token = sessionService.getToken();
        serverService.endSession(token);
    }

    public void checkAuthorized() {
        if (sessionService.isNotAuthorized()) {
            throw new UserNotAuthorizedException();
        }
    }
}
