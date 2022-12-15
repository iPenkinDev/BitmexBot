package org.ipenkin.framework;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocket extends Endpoint {

    private String url;
    private StringBuilder output;
    private Session session;

    public WebSocket(String url) {
        super();
        this.url = url;
        this.output = new StringBuilder();
    }

    public StringBuilder getOutput() {
        return output;
    }

    public void sendMessage(String message) {
        session.getAsyncRemote().sendText(message);
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;

        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                output.replace(0, message.length(), message);
            }
        });

    }

    public void connect() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
                .configurator(new ClientEndpointConfig.Configurator())
                .build();
        try {
            container.connectToServer(this, config, new URI(url));
        } catch (DeploymentException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void onError(Session session, Throwable throwable) {
        super.onError(session, throwable);
    }
}
