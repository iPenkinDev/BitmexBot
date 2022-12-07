package org.ipenkin;

import org.ipenkin.authentication.Signature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;


public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://testnet.bitmex.com";
        String apiKey = "9dOh4fBzrinfYM3QHt-pDQkV";
        String apiSecret = "1ke8EFliDV2NIfEXx3_gF73eDJjyUMbHumQ_GDIqc5cujgVb";
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
