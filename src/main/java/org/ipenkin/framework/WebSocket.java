package org.ipenkin.framework;

import com.google.gson.Gson;
import org.ipenkin.authentication.HMAC;
import org.ipenkin.authentication.Signature;
import org.ipenkin.framework.constants.URL.UtilURL;
import org.ipenkin.framework.constants.Verb;
import org.ipenkin.model.Model;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class WebSocket extends WebSocketClient {

    private static Gson gson = new Gson();


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
                System.out.println("avgPx=" + data.getAvgPx());
            }
            System.out.println(pojo.getData());
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