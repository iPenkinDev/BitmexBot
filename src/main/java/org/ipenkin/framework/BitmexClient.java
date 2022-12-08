package org.ipenkin.framework;

import java.net.http.HttpClient;

public class BitmexClient {
    private final HttpClient client;

    private BitmexClient() {
       client = HttpClient.newBuilder().build();
    }

    public BitmexClient newClient() {
        return new BitmexClient();
    }
}
