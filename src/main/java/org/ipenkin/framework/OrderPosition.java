package org.ipenkin.framework;

public class OrderPosition {
    private String orderID;
    private Integer currentQty;
    private Integer avgEntryPrice;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

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
                "orderID='" + orderID + '\'' +
                ", currentQty=" + currentQty +
                ", avgEntryPrice=" + avgEntryPrice +
                '}';
    }
}
