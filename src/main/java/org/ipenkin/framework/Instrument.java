package org.ipenkin.framework;

public class Instrument {
    private Double lastPrice;

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "lastPrice=" + lastPrice +
                '}';
    }
}
