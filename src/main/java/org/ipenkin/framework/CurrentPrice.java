package org.ipenkin.framework;

public class CurrentPrice {
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CurrentPrice{" +
                "price=" + price +
                '}';
    }
}
