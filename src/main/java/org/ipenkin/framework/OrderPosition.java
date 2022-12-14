package org.ipenkin.framework;

public class OrderPosition {
    private Integer currentQty;
    private Integer avgEntryPrice;

    public Integer getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Integer currentQty) {
        this.currentQty = currentQty;
    }

    public Integer getAvgEntryPrice() {
        return avgEntryPrice;
    }

    public void setAvgEntryPrice(Integer avgEntryPrice) {
        this.avgEntryPrice = avgEntryPrice;
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "currentQty=" + currentQty +
                ", avgEntryPrice=" + avgEntryPrice +
                '}';
    }
}
