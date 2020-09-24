package com.trafigura.poc.equity.service.impl;

import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.domain.entity.Transaction;
import com.trafigura.poc.equity.exception.NoTranactionExistException;
import com.trafigura.poc.equity.exception.TransactionAlreadyCanceledException;
import com.trafigura.poc.equity.service.TradeService;

/**
 * Created by JACK YANG on 2020/9/23.
 */
public class CancelAction extends CommandChainBase {

    public CancelAction(TradeService service, Trade t, CommandChain next) {
        super(service, t, next);
    }

    @Override
    public boolean accept() {
        return getTrade().getAction() == TradeAction.CANCEL;
    }

    @Override
    public void check() {
        Transaction transaction = tradeService.getLatestTransaction(getTrade().getTradeId())
                .orElseThrow(() -> new NoTranactionExistException("Cancel should NOT be the the first transaction: "  + getTrade()));

        if(transaction.getAction() == TradeAction.CANCEL) {
            throw new TransactionAlreadyCanceledException("Transaction Already cancelled " + transaction);
        }

    }

    @Override
    public void updatePosition() {
        super.updatePosition(undo());
    }

    @Override
    public void postAction() {

    }

}
