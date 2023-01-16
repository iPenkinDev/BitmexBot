package org.ipenkin.model;

public class Bot {
    private String apiKey;
    private String apiSecret;
    private double step;
    private int level;
    private double coef;

    public Bot(String apiKey, String apiSecret, Double step, Integer level, Double coef) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.step = step;
        this.level = level;
        this.coef = coef;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }
}
