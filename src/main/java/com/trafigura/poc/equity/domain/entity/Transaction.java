package com.trafigura.poc.equity.domain.entity;

import com.trafigura.poc.equity.domain.BuySell;
import com.trafigura.poc.equity.domain.TradeAction;

import javax.persistence.*;

/**
 * Created by JACK YANG on 2020/9/23.
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tradeId;

    private Integer version;

    private String securityCode;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TradeAction action;

    @Enumerated(EnumType.STRING)
    private BuySell buySell;

    public Long getId() {
        return id;
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
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", tradeId='" + tradeId + '\'' +
                ", version=" + version +
                ", securityCode='" + securityCode + '\'' +
                ", quantity=" + quantity +
                ", action=" + action +
                ", buySell=" + buySell +
                '}';
    }
}
