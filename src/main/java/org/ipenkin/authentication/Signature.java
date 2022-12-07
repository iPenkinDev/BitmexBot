package org.ipenkin.authentication;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Signature {

    public byte[] createSignature(String verb, String path,  String data,String expires, String apiSecret) {
        HMAC hmac = new HMAC();
        return hmac.calcHmacSha256(apiSecret.getBytes(StandardCharsets.UTF_8),
                (verb + path + expires + data).getBytes(StandardCharsets.UTF_8));
    }

    public String signatureToString(byte[] signature) {
        String signatureStr = String.format("%032x", new BigInteger(1, signature));
        System.out.println(signatureStr);
        return signatureStr;
    }
}
