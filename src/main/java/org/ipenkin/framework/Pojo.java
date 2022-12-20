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
        private Double orderQty;
        private Double avgPx;

        @Override
        public String toString() {
            return "Data{" +
                    "orderID='" + getOrderID() + '\'' +
                    ", orderQty=" + getOrderQty() +
                    ", avgPx=" + getAvgPx() +
                    '}';
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public Double getOrderQty() {
            return orderQty;
        }

        public void setOrderQty(Double orderQty) {
            this.orderQty = orderQty;
        }

        public Double getAvgPx() {
            return avgPx;
        }

        public void setAvgPx(Double avgPx) {
            this.avgPx = avgPx;
        }
    }
}

