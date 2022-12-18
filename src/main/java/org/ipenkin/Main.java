package org.ipenkin;

import com.google.gson.Gson;
import org.ipenkin.framework.BitmexClient;
import org.ipenkin.framework.CurrentPrice;
import org.ipenkin.framework.WebSocket;
import org.ipenkin.framework.OrderPosition;
import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.Symbol;
import org.ipenkin.framework.constants.URL.UtilURL;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.model.Model;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
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
        model = new Model(Model.getApiKey(), Model.getApiSecret(), 100.0, 3, 10.0);
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

        try {
            WebSocket newWebSocket = new WebSocket(new URI(UtilURL.createWebsocketURL()));
            newWebSocket.connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
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
        //System.out.println("\n" + "current price=" + currentPrice);
    }

    private static void orderPosition(BitmexClient bitmexClient) {
        HttpResponse<String> responseGetPosition = bitmexClient.getPosition();
        String jsonString = responseGetPosition.body();
        System.out.println(jsonString);
        Gson gson = new Gson();
        OrderPosition position = new OrderPosition();
        OrderPosition[] pos = gson.fromJson(jsonString, OrderPosition[].class);
        System.out.println("\n" + pos[0]);

    }
}
