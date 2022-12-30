package org.ipenkin.authentication.constants.URL;

public class ResourceURL {
    public static final String ORDER = "/order";
    public static final String POSITION = "/position?filter=%7B%22symbol%22%3A%20%22XBTUSD%22%7D";
    public static final String INSTRUMENT = "/instrument?symbol=XBTUSD&count=100&reverse=false";
    public static final String ORDERBOOK = "/orderBook/L2?symbol=XBTUSD&depth=1";
}
