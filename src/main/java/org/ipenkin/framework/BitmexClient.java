package org.ipenkin.framework;

import org.ipenkin.framework.order.Order;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BitmexClient {
    private final HttpClient client;

    private BitmexClient() {
       client = HttpClient.newBuilder().build();
    }

    public BitmexClient newClient() {
        return new BitmexClient();
    }

//    public HttpResponse<String> sendOrder(Order order) {
//        HttpRequest httpRequest = HttpRequest.newBuilder()
//              .
//    }
}
