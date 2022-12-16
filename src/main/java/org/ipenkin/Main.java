package org.ipenkin;

import com.google.gson.Gson;
import org.ipenkin.authentication.HMAC;
import org.ipenkin.authentication.Signature;
import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.CurrentPrice;
import org.ipenkin.framework.OrderPosition;
import org.ipenkin.framework.WebSocket;
import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.Symbol;
import org.ipenkin.framework.constants.URL.UtilURL;
import org.ipenkin.framework.constants.Verb;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.model.Model;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("InstantiationOfUtilityClass")
public class Main {
    private static Model model;
    private static Double currentPrice;
    private static Double entryPrice;
    private static List<LimitOrder> limitOrders;

    public static void main(String[] args) {
        limitOrders = new ArrayList<>();
        model = new Model(Model.getApiKey(), Model.getApiSecret(), 100.0, 2, 300.0);
        BitmexClient bitmexClient = new BitmexClient(Model.getApiKey(), Model.getApiSecret(), true);
        currentMarketPrice(bitmexClient);
        entryPrice = currentPrice - Model.getStep();

        for (int i = 0; i < Model.getLevel(); i++) {
            System.out.println("entry price=" + entryPrice);
            limitOrders.add(new LimitOrder(Symbol.XBTUSD, OrderSide.Buy, Model.getCoef(), entryPrice, null));

            entryPrice = entryPrice - Model.getStep();
            HttpResponse<String> httpResponse = bitmexClient.sendOrder(limitOrders.get(i));
            httpResponse.body();

        }
        orderPosition(bitmexClient);

        doConnect();
    }

    private static void doConnect() {
        WebSocket webSocket = new WebSocket(UtilURL.createWebsocketURL());
        webSocket.connect();

        String expires = String.valueOf(Instant.now().getEpochSecond() + 10);
        String signature = Signature.signatureToString(HMAC.calcHmacSha256(Model.getApiSecret().getBytes(StandardCharsets.UTF_8),
                (Verb.GET + UtilURL.REALTIME + expires).getBytes(StandardCharsets.UTF_8)));

        webSocket.sendMessage("{\"op\": \"authKeyExpires\", \"args\": [\"" + Model.getApiKey() + "\", " + expires + ", \"" + signature + "\"]}");
        webSocket.sendMessage("{\"op\": \"subscribe\", \"args\": [\"order\"]}");

        String previousX = "";
        while (true) {
            String x = webSocket.getOutput().toString();

            if (!x.equals(previousX)) {
                System.out.println(x);
            }
            previousX = x;
        }
    }


    private static void currentMarketPrice(BitmexClient bitmexClient) {
        HttpResponse<String> response = bitmexClient.getInstrumentPrice();
        String jsonString = response.body();
        System.out.println(jsonString);
        Gson gson = new Gson();
        CurrentPrice inst = new CurrentPrice();
        CurrentPrice[] price = gson.fromJson(jsonString, CurrentPrice[].class);
        System.out.println("\n" + price[1]);
        inst.setPrice(price[1].getPrice());
        currentPrice = inst.getPrice();
        System.out.println("\n" + "current price=" + currentPrice);
    }

    private static void orderPosition(BitmexClient bitmexClient) {
        HttpResponse<String> responseGetPosition = bitmexClient.getPosition();
        String jsonString = responseGetPosition.body();
       // System.out.println(jsonString);
        Gson gson = new Gson();
        OrderPosition position = new OrderPosition();
        OrderPosition[] pos = gson.fromJson(jsonString, OrderPosition[].class);
        //System.out.println("\n" + pos[0]);

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
