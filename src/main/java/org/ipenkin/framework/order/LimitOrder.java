package org.ipenkin.framework.order;

import org.ipenkin.framework.constants.OrderSide;
import org.ipenkin.framework.constants.OrderType;
import org.ipenkin.framework.constants.Symbol;

public class LimitOrder {
    private Symbol symbol;
    private OrderSide side;
    private Double simpleOrderQty;
    private Double orderQty;
    private Double price;
    private Double displayQty;
    private Double stopPx;
    private String clOrdID;
    private String clOrdLinkID;
    private Double pegOffsetValue;
    private String pegPriceType;
    private OrderType ordType;
    private String timeInForce;
    private String execInst;
    private String contingencyType;
    private String text;

    public LimitOrder(Symbol symbol, OrderSide side, Double simpleOrderQty, Double orderQty, Double price, Double displayQty, Double stopPx, String clOrdID, String clOrdLinkID, Double pegOffsetValue, String pegPriceType, OrderType ordType, String timeInForce, String execInst, String contingencyType, String text) {
        this.symbol = symbol;
        this.side = side;
        this.simpleOrderQty = simpleOrderQty;
        this.orderQty = orderQty;
        this.price = price;
        this.displayQty = displayQty;
        this.stopPx = stopPx;
        this.clOrdID = clOrdID;
        this.clOrdLinkID = clOrdLinkID;
        this.pegOffsetValue = pegOffsetValue;
        this.pegPriceType = pegPriceType;
        this.ordType = ordType;
        this.timeInForce = timeInForce;
        this.execInst = execInst;
        this.contingencyType = contingencyType;
        this.text = text;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public Double getSimpleOrderQty() {
        return simpleOrderQty;
    }

    public void setSimpleOrderQty(Double simpleOrderQty) {
        this.simpleOrderQty = simpleOrderQty;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDisplayQty() {
        return displayQty;
    }

    public void setDisplayQty(Double displayQty) {
        this.displayQty = displayQty;
    }

    public Double getStopPx() {
        return stopPx;
    }

    public void setStopPx(Double stopPx) {
        this.stopPx = stopPx;
    }

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    public String getClOrdLinkID() {
        return clOrdLinkID;
    }

    public void setClOrdLinkID(String clOrdLinkID) {
        this.clOrdLinkID = clOrdLinkID;
    }

    public Double getPegOffsetValue() {
        return pegOffsetValue;
    }

    public void setPegOffsetValue(Double pegOffsetValue) {
        this.pegOffsetValue = pegOffsetValue;
    }

    public String getPegPriceType() {
        return pegPriceType;
    }

    public void setPegPriceType(String pegPriceType) {
        this.pegPriceType = pegPriceType;
    }

    public OrderType getOrdType() {
        return ordType;
    }

    public void setOrdType(OrderType ordType) {
        this.ordType = ordType;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getExecInst() {
        return execInst;
    }

    public void setExecInst(String execInst) {
        this.execInst = execInst;
    }

    public String getContingencyType() {
        return contingencyType;
    }

    public void setContingencyType(String contingencyType) {
        this.contingencyType = contingencyType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
