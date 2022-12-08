package org.ipenkin.model;

import java.time.Instant;

public class Model {
    private static String apiKey;
    private static String apiSecret;
    private static double step;
    private static double level;
    private static double coef;

    public static String getApiKey() {
        return apiKey;
    }

    public static String getApiSecret() {
        return apiSecret;
    }

    public static double getStep() {
        return step;
    }

    public static double getLevel() {
        return level;
    }

    public static double getCoef() {
        return coef;
    }
}
