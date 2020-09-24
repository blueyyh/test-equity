package com.trafigura.poc.equity.service;

import com.trafigura.poc.equity.domain.SecurityPosition;
import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.entity.Transaction;
import com.trafigura.poc.equity.exception.InvalidTradeDataException;
import com.trafigura.poc.equity.service.impl.CommandChain;

import java.util.List;
import java.util.Optional;

/**
 * Created by JACK YANG on 2020/9/23.
 */
public interface TradeService {
    Optional<Transaction> getLatestTransaction(String tradeId);

    void updatePosition(String securityCode, Integer position);

    Transaction saveTransaction(Trade trade);

    List<SecurityPosition> currentPosition();

    void validateTrade(Trade trade) throws InvalidTradeDataException;

    CommandChain commandChain(Trade trade);
}
