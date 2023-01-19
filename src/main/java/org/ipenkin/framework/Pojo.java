package org.ipenkin.framework;

import java.util.List;

public class Pojo {
    private List<Data> data;

    @Override
    public String toString() {
        return "Pojo{" +
                "data=" + getData() +
                '}';
    }

    public List<Data> getData() {
        return data;
    }

    class Data {
        private String orderID;
        private String side;
        private String ordStatus;
        private Double avgPx;

        @Override
        public String toString() {
            return "Data{" +
                    "orderID='" + orderID + '\'' +
                    ", side='" + side + '\'' +
                    ", ordStatus='" + ordStatus + '\'' +
                    ", avgPx=" + avgPx +
                    '}';
        }

        public String getOrdStatus() {
            return ordStatus;
        }

        public void setOrdStatus(String ordStatus) {
            this.ordStatus = ordStatus;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getSide() {
            return side;
        }

        public void setSide(String side) {
            this.side = side;
        }

        public Double getAvgPx() {
            return avgPx;
        }

        public void setAvgPx(Double avgPx) {
            this.avgPx = avgPx;
        }
    }
}
