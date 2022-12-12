package org.ipenkin.model;

public class Model {
    private static String apiKey = "6zP3XL1ssGDrlU74dyJxKhFf";
    private static String apiSecret = "kuK2CWx5RsLI68b4DZLnIX16XyqTupwFc3jTe8IWjmr0JN0E";
    private static double step;
    private static int level;
    private static double coef;

    public Model(Double step, Integer level, Double coef) {
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
