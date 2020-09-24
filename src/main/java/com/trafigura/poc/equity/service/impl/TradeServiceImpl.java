package com.trafigura.poc.equity.service.impl;

import com.trafigura.poc.equity.domain.SecurityPosition;
import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.entity.Transaction;
import com.trafigura.poc.equity.exception.InvalidTradeDataException;
import com.trafigura.poc.equity.repository.TransactionRepository;
import com.trafigura.poc.equity.service.TradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * Created by JACK YANG on 2020/9/23.
 */
@Service
public class TradeServiceImpl implements TradeService {

    //spring default component is singleton, so it's safe not use static member
    //for simplicity we use map instead of database updating
    private final Map<String, Integer> positionMap = new ConcurrentHashMap<>();

    private final TransactionRepository transactionRepository;

    public TradeServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<Transaction> getLatestTransaction(String tradeId) {
        return transactionRepository.findTopByTradeIdOrderByVersionDesc(tradeId);
    }

    @Override
    public void updatePosition(String securityCode, Integer position) {
        positionMap.put(securityCode, positionMap.getOrDefault(securityCode, 0) + position);
    }

    @Override
    public Transaction saveTransaction(Trade trade) {
        Transaction transaction = tradeToTransaction(trade);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<SecurityPosition> currentPosition() {

        return positionMap.entrySet().stream().map(entry -> new SecurityPosition(entry.getKey(), entry.getValue())).collect(Collectors.toList());

    }

    private Transaction tradeToTransaction(Trade trade) {
        Transaction transaction = new Transaction();
        transaction.setAction(trade.getAction());
        transaction.setBuySell(trade.getBuySell());
        transaction.setQuantity(trade.getQuantity());
        transaction.setSecurityCode(trade.getSecurityCode());
        transaction.setVersion(trade.getVersion());
        transaction.setTradeId(trade.getTradeId());
        return transaction;
    }

    public void validateTrade(Trade trade) throws InvalidTradeDataException {
        //todo 1.trade and its mandatory field not null

        //todo 2. check trade.getSecurityCode() base on database table(load in cache) for meta data dictionary


    }

    public CommandChain commandChain(Trade trade) {
        return new InsertAction(this, trade,
                new UpdateAction(this, trade,
                        new CancelAction(this, trade, () -> false)));

    }
}
