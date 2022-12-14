package org.ipenkin.framework;

import com.google.gson.Gson;
import org.ipenkin.authentication.Signature;
import org.ipenkin.framework.constants.URL.ResourceURL;
import org.ipenkin.framework.constants.URL.URL;
import org.ipenkin.framework.constants.URL.URLBuilder;
import org.ipenkin.framework.constants.URL.UtilURL;
import org.ipenkin.framework.constants.Verb;
import org.ipenkin.framework.order.Order;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

public class BitmexClient {
    private boolean isTestnet;
    private final HttpClient client;
    private String apiKey;
    private String apiSecret;

    public BitmexClient(String apiKey, String apiSecret, boolean isTestnet) {
        client = HttpClient.newHttpClient();
        this.isTestnet = isTestnet;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public HttpResponse<String> getPosition() {
        URL url = new URLBuilder()
                .protocol(UtilURL.PROTOCOL)
                .net(getNet())
                .baseUrl(UtilURL.BASE_URL)
                .apiPath(UtilURL.API_PATH)
                .resourcePath(ResourceURL.POSITION)
                .build();
        String data = "";
        String expires = createExpires();
        String signature = createSignature(url, Verb.GET.toString(), data, expires);
        while (signature.length() != 64) {
            expires = createExpires();
            signature = createSignature(url, Verb.GET.toString(), data, expires);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .header("api-signature", signature)
                .header("api-expires", expires)
                .header("api-key", apiKey)
                .uri(URI.create(url.toString()))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response =client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public HttpResponse<String> sendOrder(Order order) {
        URL url = new URLBuilder()
                .protocol(UtilURL.PROTOCOL)
                .net(getNet())
                .baseUrl(UtilURL.BASE_URL)
                .apiPath(UtilURL.API_PATH)
                .resourcePath(ResourceURL.ORDER)
                .build();
        String orderToJson = new Gson().toJson(order);

        String expires = createExpires();
        String signature = createSignature(url, Verb.POST.toString(), orderToJson, expires);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(orderToJson))
                .header("api-signature", signature)
                .header("api-expires", expires)
                .header("api-key", apiKey)
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Accept", "application/json")
                .uri(URI.create(url.toString()))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public HttpResponse<String> getInstrumentPrice() {
        URL url = new URLBuilder()
                .protocol(UtilURL.PROTOCOL)
                .net(getNet())
                .baseUrl(UtilURL.BASE_URL)
                .apiPath(UtilURL.API_PATH)
                .resourcePath(ResourceURL.ORDERBOOK)
                .build();
        String data = "";
        String expires = createExpires();
        String signature = createSignature(url, Verb.GET.toString(), data, expires);
        while (signature.length() != 64) {
            expires = createExpires();
            signature = createSignature(url, Verb.GET.toString(), data, expires);
        }
        HttpRequest request = HttpRequest.newBuilder()
                .header("api-signature", signature)
                .header("api-expires", expires)
                .header("api-key", apiKey)
                .uri(URI.create(url.toString()))
                .GET()
                .build();
        HttpResponse<String> response;
        try {
            response =client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;

    }

    private String createSignature(URL url, String verb, String data, String expires) {
        Signature signature = new Signature();
        String path = url.getApiPath() + url.getResourcePath();
        System.out.println(verb + " " + path + " " + data + " " + expires + apiSecret);
        return signature.signatureToString(signature.createSignature(verb, path, data, expires, apiSecret));
    }

    private String getNet() {
        return isTestnet ? UtilURL.TEST_NET : UtilURL.REAL_NET;
    }

    private String createExpires() {
        return String.valueOf(Instant.now().getEpochSecond() + 10);

    }
}
