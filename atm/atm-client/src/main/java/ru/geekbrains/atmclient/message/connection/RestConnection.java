package ru.geekbrains.atmclient.message.connection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.atmclient.message.ServerMessage;

@Component
@RequiredArgsConstructor
public class RestConnection implements ServerConnection {

    private final RestTemplate restTemplate;
    @Value("${app.remote-server.url}")
    private String serverAddress;
    @Value("${app.remote-server.operation-url}")
    private String operationUrl;

    public <T> T sendMessageAndGetResponse(ServerMessage message, Class<T> responseType) {
        return restTemplate.postForObject(serverAddress + operationUrl, message, responseType);
    }
}
