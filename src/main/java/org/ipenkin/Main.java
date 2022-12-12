package org.ipenkin;

import com.google.gson.*;
import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.Instrument;
import org.ipenkin.model.Model;

import java.net.http.HttpResponse;
import java.util.Arrays;


@SuppressWarnings("InstantiationOfUtilityClass")
public class Main {
    private static Model model;
    private static Double currentPrice;

    public static void main(String[] args) {
        model = new Model(200.0, 3, 100.0);
        BitmexClient bitmexClient = new BitmexClient(Model.getApiKey(), Model.getApiSecret(), true);

//        LimitOrder limitOrder = new LimitOrder(Symbol.XBTUSD, OrderSide.Buy, Model.getCoef(), 17122.0, null);
//        HttpResponse<String> httpResponse = bitmexClient.sendOrder(limitOrder);
//        httpResponse.body();

        currentMarketPrice(bitmexClient);


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
