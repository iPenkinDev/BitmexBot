package org.ipenkin.model;

public class Model {
    private static String apiKey = "SSijwr_9yp84o8Juy8Cm644T";
    private static String apiSecret = "iKwTxNOlzS6iITCdEPHfGx8SQW4HK9_Dfvnl3NUwuBf12n48";
    private static double step;
    private static int level;
    private static double coef;

    public Model(String apiKey, String apiSecret, Double step, Integer level, Double coef) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.step = step;
        this.level = level;
        this.coef = coef;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getApiSecret() {
        return apiSecret;
    }

    public static double getStep() {
        return step;
    }

    public static void setStep(double step) {
        Model.step = step;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Model.level = level;
    }

    public static double getCoef() {
        return coef;
    }

    public static void setCoef(double coef) {
        Model.coef = coef;
    }
}
