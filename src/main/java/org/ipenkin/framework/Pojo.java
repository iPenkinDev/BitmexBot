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

    public void setData(List<Data> data) {
        this.data = data;
    }

    class Data {
        private String orderID;
        private String side;
        private String orderStatus;
        private Double avgPx;

        @Override
        public String toString() {
            return "Data{" +
                    "orderID='" + orderID + '\'' +
                    ", side='" + side + '\'' +
                    ", orderStatus='" + orderStatus + '\'' +
                    ", avgPx=" + avgPx +
                    '}';
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
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

