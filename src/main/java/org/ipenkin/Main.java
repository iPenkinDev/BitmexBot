package org.ipenkin;

import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.constants.ExecutionInstructions;
import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.Symbol;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.framework.order.MarketOrder;
import org.ipenkin.model.Model;

import java.net.http.HttpResponse;


public class Main {
    private static Model model;

    public static void main(String[] args) {

        BitmexClient bitmexClient = new BitmexClient(Model.getApiKey(), Model.getApiSecret(), true);
        model = new Model(100.0, 2.0, 3.0);
        MarketOrder marketOrder = new MarketOrder(Symbol.XBTUSD, OrderSide.Buy, 200.0, ExecutionInstructions.Close);
        LimitOrder limitOrder = new LimitOrder(Symbol.XBTUSD,OrderSide.Buy, 200.0, 17122.0, ExecutionInstructions.Close);

//        HttpResponse<String> httpResponse = bitmexClient.sendOrder(limitOrder);
//        System.out.println(httpResponse.body());
//
//            HttpResponse<String> httpResponse = bitmexClient.sendOrder(marketOrder);
//            httpResponse.body();

//        HttpResponse<String> response = bitmexClient.getPosition();
//        System.out.println(response.body());
    }

//    private static void testQuery() {
//        String baseUrl = "https://testnet.bitmex.com";
//        String apiKey = "6zP3XL1ssGDrlU74dyJxKhFf";
//        String apiSecret = "kuK2CWx5RsLI68b4DZLnIX16XyqTupwFc3jTe8IWjmr0JN0E";
//        String verb = "GET";
//        String path = "/api/v1/user/wallet";
//        String expires = String.valueOf(Instant.now().getEpochSecond() + 100);
//        String data = "";
//
//        Signature signature = new Signature();
//        String signatureStr = signature.signatureToString(signature.createSignature(verb, path, data, expires, apiSecret));
//
//        HttpClient client = HttpClient.newBuilder().build();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .header("api-expires", expires)
//                .header("api-key", apiKey)
//                .header("api-signature", signatureStr)
//                .uri(URI.create(baseUrl + path))
//                .GET()
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
