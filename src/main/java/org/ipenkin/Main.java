package org.ipenkin;

import com.google.gson.*;
import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.Instrument;
import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.Symbol;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.framework.order.Order;
import org.ipenkin.model.Model;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("InstantiationOfUtilityClass")
public class Main {
    private static Model model;
    private static Double currentPrice;
    private static Double entryPrice;
    private static List<LimitOrder> limitOrders;

    public static void main(String[] args) {
        limitOrders = new ArrayList<>();
        model = new Model(100.0, 5, 100.0);
        BitmexClient bitmexClient = new BitmexClient(Model.getApiKey(), Model.getApiSecret(), true);
        currentMarketPrice(bitmexClient);
        entryPrice = currentPrice - Model.getStep();

        for (int i = 0; i < Model.getLevel(); i++) {
            limitOrders.add(new LimitOrder(Symbol.XBTUSD, OrderSide.Buy, Model.getCoef(), entryPrice, null));

            entryPrice = entryPrice - Model.getStep();
            System.out.println(entryPrice);
            HttpResponse<String> httpResponse = bitmexClient.sendOrder(limitOrders.get(i));
            httpResponse.body();
        }

        // LimitOrder limitOrder = new LimitOrder(Symbol.XBTUSD, OrderSide.Buy, Model.getCoef(), entryPrice, null);
    }

    private static void currentMarketPrice(BitmexClient bitmexClient) {
        HttpResponse<String> response = bitmexClient.getInstrumentPrice();
        String jsonString = response.body();

        Gson gson = new Gson();
        Instrument inst = new Instrument();
        Instrument[] instrument = gson.fromJson(jsonString, Instrument[].class);
        System.out.println("\n" + instrument[0]);
        inst.setLastPrice(instrument[0].getLastPrice());
        currentPrice = inst.getLastPrice();
        System.out.println("\n" + "current price=" + currentPrice);
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
