package org.ipenkin.framework;

import com.google.gson.Gson;
import org.ipenkin.authentication.HMAC;
import org.ipenkin.authentication.Signature;
import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.Symbol;
import org.ipenkin.framework.constants.URL.UtilURL;
import org.ipenkin.framework.constants.Verb;
import org.ipenkin.framework.order.LimitOrder;
import org.ipenkin.model.Model;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class WebSocket extends WebSocketClient {

    private static Gson gson = new Gson();
    private static Double entryPriceAfterReOrder;


    public WebSocket(URI serverURI) {
        super(serverURI);
    }

    String expires = String.valueOf(Instant.now().getEpochSecond() + 10);
    String signature = Signature.signatureToString(HMAC.calcHmacSha256(Model.getApiSecret().getBytes(StandardCharsets.UTF_8),
            (Verb.GET + UtilURL.REALTIME + expires).getBytes(StandardCharsets.UTF_8)));


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("{\"op\": \"authKeyExpires\", \"args\": [\"" + Model.getApiKey() + "\", " + expires + ", \"" + signature + "\"]}");
        send("{\"op\": \"subscribe\", \"args\": [\"order\"]}");
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
        Pojo pojo = gson.fromJson(message, Pojo.class);
        System.out.println("-------------------------------------------------");
        System.out.println("pojo=" + pojo);
        if (pojo.getData() != null) {
            for (Pojo.Data data : pojo.getData()) {
                System.out.println("-------------------------------------------------");
                System.out.println("data=" + data + "\n");
                System.out.println("orderID=" + data.getOrderID());
                System.out.println("side=" + data.getSide());
                System.out.println("orderStatus=" + data.getOrdStatus());
                System.out.println("avgPx=" + data.getAvgPx());
                System.out.println("-------------------------------------------------");


                if (data.getAvgPx() != null && data.getSide() != null && data.getSide().equals("Buy") && data.getOrdStatus().equals("Filled")) {
                    entryPriceAfterReOrder = data.getAvgPx() + Model.getStep();
                    System.out.println("order selled");
                    System.out.println("entryPriceAfterReOrder=" + entryPriceAfterReOrder);
                    LimitOrder limitOrder = new LimitOrder(Symbol.XBTUSD, OrderSide.Sell, Model.getCoef(), entryPriceAfterReOrder, null);
                    HttpResponse<String> response = new BitmexClient(Model.getApiKey(), Model.getApiSecret(), true)
                            .sendOrder(limitOrder);
                    System.out.println(response.body());
                }
                if (data.getAvgPx() != null && data.getSide() != null && data.getSide().equals("Sell") && data.getOrdStatus().equals("Filled")) {
                    entryPriceAfterReOrder = data.getAvgPx() + Model.getStep();
                    System.out.println("order buyed");
                    System.out.println("entryPriceAfterReOrder=" + entryPriceAfterReOrder);
                    LimitOrder limitOrder = new LimitOrder(Symbol.XBTUSD, OrderSide.Buy, Model.getCoef(), entryPriceAfterReOrder, null);
                    HttpResponse<String> httpResponse = new BitmexClient(Model.getApiKey(), Model.getApiSecret(), true)
                            .sendOrder(limitOrder);
                    System.out.println(httpResponse.body());
                }


            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }


}