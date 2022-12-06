package org.ipenkin.authentication;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Signature {

    public byte[] createSignature(String verb, String path, String expires, String data, String apiSecret) {
        HMAC hmac = new HMAC();
        return hmac.calcHmacSha256(apiSecret.getBytes(), (verb + path + expires + data).getBytes());
    }

    public String signatureToString(byte[] signature) {
        String signatureStr = String.format("Hex: %064x", new BigInteger(1, signature));
        System.out.println("Signature: " + signatureStr);
        return signatureStr;
    }
}
