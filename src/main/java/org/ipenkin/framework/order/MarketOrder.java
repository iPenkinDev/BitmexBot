package org.ipenkin.framework.order;

import org.ipenkin.authentication.constants.ExecutionInstructions;
import org.ipenkin.authentication.constants.OrderSide;
import org.ipenkin.authentication.constants.OrderType;
import org.ipenkin.authentication.constants.Symbol;

public class MarketOrder implements Order{
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
    private OrderType ordType = OrderType.Market;
    private String timeInForce;
    private ExecutionInstructions execInst;
    private String contingencyType;
    private String text;

    public MarketOrder(Symbol symbol, OrderSide side, Double orderQty, ExecutionInstructions execInst) {
        this.symbol = symbol;
        this.side = side;
        this.orderQty = orderQty;
        this.execInst = execInst;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side.toString();
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

    public String getOrdType() {
        return ordType.toString();
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
        return execInst.toString();
    }

    public void setExecInst(ExecutionInstructions execInst) {
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
