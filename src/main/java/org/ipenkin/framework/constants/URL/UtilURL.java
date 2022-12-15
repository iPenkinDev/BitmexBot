package org.ipenkin.framework.constants.URL;

public class UtilURL {
    public static final String PROTOCOL = "https://";
    public static final String TEST_NET = "testnet.";
    public static final String REAL_NET = "www.";
    public static final String BASE_URL = "bitmex.com";
    public static final String API_PATH = "/api/v1";
    public static final String WEBSOCKET = "wss://ws.";
    public static final String REALTIME = "/realtime";

    public static String createWebsocketURL() {
        return WEBSOCKET + TEST_NET + BASE_URL + REALTIME;
    }
}
