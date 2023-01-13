package org.ipenkin;

import com.google.gson.Gson;
import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.CurrentPrice;
import org.ipenkin.framework.Pojo;
import org.ipenkin.framework.WebSocket;
import org.ipenkin.authentication.constants.OrderSide;
import org.ipenkin.authentication.constants.Symbol;
import org.ipenkin.authentication.constants.URL.UtilURL;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.model.Bot;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("InstantiationOfUtilityClass")
public class Main {
    private Double currentPrice;
    private Double entryPrice;
    private List<LimitOrder> limitOrders;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        limitOrders = new ArrayList<>();
        Bot model = new Bot("SSijwr_9yp84o8Juy8Cm644T", "iKwTxNOlzS6iITCdEPHfGx8SQW4HK9_Dfvnl3NUwuBf12n48", 1.0, 3, 100.0);

        BitmexClient bitmexClient = new BitmexClient(model.getApiKey(), model.getApiSecret(), true);
        currentMarketPrice(bitmexClient);
        entryPrice = currentPrice - model.getStep();

        for (int i = 0; i < model.getLevel(); i++) {
            System.out.println("entry price=" + entryPrice);
            limitOrders.add(new LimitOrder(Symbol.XBTUSD, OrderSide.Buy, model.getCoef(), entryPrice, null));
            entryPrice = entryPrice - model.getStep();
            while (true) {
                HttpResponse<String> httpResponse = bitmexClient.sendOrder(limitOrders.get(i));
                String body = httpResponse.body();
                System.out.println(body);
                boolean failed = body.contains("error");
                if (!failed) {
                    break;
                }
                try {
                    System.err.println("order creation failed, retrying with delay");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        orderPosition(bitmexClient);

        try {
            WebSocket newWebSocket = new WebSocket(new URI(UtilURL.createWebsocketURL()), model);
            newWebSocket.connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void currentMarketPrice(BitmexClient bitmexClient) {
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

    private void orderPosition(BitmexClient bitmexClient) {
        HttpResponse<String> responseGetPosition = bitmexClient.getPosition();
        String jsonString = responseGetPosition.body();
        System.out.println(jsonString);
        Gson gson = new Gson();
        Pojo position = new Pojo();
        Pojo[] pos = gson.fromJson(jsonString, Pojo[].class);
        System.out.println("Size pos array=" + pos.length);
        System.out.println("\n" + pos[0]);
    }
}