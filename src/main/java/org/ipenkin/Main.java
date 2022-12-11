package org.ipenkin;

import com.google.gson.Gson;
import org.ipenkin.authentication.Signature;
import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.OrderType;
import org.ipenkin.framework.constants.Symbol;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.framework.order.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;


public class Main {
    public static void main(String[] args) {
        String apiKey = "6zP3XL1ssGDrlU74dyJxKhFf";
        String apiSecret = "kuK2CWx5RsLI68b4DZLnIX16XyqTupwFc3jTe8IWjmr0JN0E";

        BitmexClient bitmexClient = new BitmexClient(apiKey, apiSecret, true);
        LimitOrder order = new LimitOrder(Symbol.XBTUSD, OrderSide.SELL,100.0,19000.0);

        HttpResponse<String> response = bitmexClient.sendOrder(order);
        System.out.println(response.body());


//        testQuery();
    }

    private static void testQuery() {
        String baseUrl = "https://testnet.bitmex.com";
        String apiKey = "6zP3XL1ssGDrlU74dyJxKhFf";
        String apiSecret = "kuK2CWx5RsLI68b4DZLnIX16XyqTupwFc3jTe8IWjmr0JN0E";
        String verb = "GET";
        String path = "/api/v1/user/wallet";
        String expires = String.valueOf(Instant.now().getEpochSecond() + 100);
        String data = "";

        Signature signature = new Signature();
        String signatureStr = signature.signatureToString(signature.createSignature(verb, path, data, expires, apiSecret));

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .header("api-expires", expires)
                .header("api-key", apiKey)
                .header("api-signature", signatureStr)
                .uri(URI.create(baseUrl + path))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
