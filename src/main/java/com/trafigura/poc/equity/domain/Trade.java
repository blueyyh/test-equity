package com.trafigura.poc.equity.domain;

import java.util.Objects;

/**
 * Created by JACK YANG on 2020/9/23.
 */
public class Trade {
    String tradeId;

    Integer version;

    String securityCode;

    Integer quantity;

    TradeAction action;

    BuySell buySell;

    public Trade(String tradeId, int version, String securityCode, int quantity, TradeAction action, BuySell buySell) {
        this.tradeId = tradeId;
        this.version = version;
        this.securityCode = securityCode;
        this.quantity = quantity;
        this.action = action;
        this.buySell = buySell;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TradeAction getAction() {
        return action;
    }

    public void setAction(TradeAction action) {
        this.action = action;
    }

    public BuySell getBuySell() {
        return buySell;
    }

    public void setBuySell(BuySell buySell) {
        this.buySell = buySell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(tradeId, trade.tradeId) &&
                Objects.equals(version, trade.version) &&
                Objects.equals(securityCode, trade.securityCode) &&
                Objects.equals(quantity, trade.quantity) &&
                action == trade.action &&
                buySell == trade.buySell;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, version, securityCode, quantity, action, buySell);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", version=" + version +
                ", securityCode='" + securityCode + '\'' +
                ", quantity=" + quantity +
                ", action=" + action +
                ", buySell=" + buySell +
                '}';
    }
}
