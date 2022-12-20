package org.ipenkin.framework;

import javax.xml.crypto.Data;
import java.util.List;
public class Pojo {
  public List<Data> data;

    @Override
    public String toString() {
        return "Pojo{" +
                "data=" + data +
                '}';
    }
    static class Data {
        public String orderID;
        public Double orderQty;
        public Double avgPx;

        @Override
        public String toString() {
            return "Data{" +
                    "orderID='" + orderID + '\'' +
                    ", orderQty=" + orderQty +
                    ", avgPx=" + avgPx +
                    '}';
        }
    }
}

