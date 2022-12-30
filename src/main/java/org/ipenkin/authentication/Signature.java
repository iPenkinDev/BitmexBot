package org.ipenkin.authentication;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Signature {

    public static byte[] createSignature(String verb, String path, String data, String expires, String apiSecret) {
        HMAC hmac = new HMAC();
        return hmac.calcHmacSha256(apiSecret.getBytes(StandardCharsets.UTF_8),
                (verb + path + expires + data).getBytes(StandardCharsets.UTF_8));
    }

    public static String signatureToString(byte[] signature) {
        return String.format("%032x", new BigInteger(1, signature));
    }
}
