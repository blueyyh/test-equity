package com.trafigura.poc.equity.service.impl;

import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.domain.entity.Transaction;
import com.trafigura.poc.equity.exception.NoTranactionExistException;
import com.trafigura.poc.equity.exception.TradeVersionNotCorrectException;
import com.trafigura.poc.equity.exception.TransactionAlreadyCanceledException;
import com.trafigura.poc.equity.service.TradeService;

/**
 * Created by JACK YANG on 2020/9/23.
 */
public class UpdateAction extends CommandChainBase {

    public UpdateAction(TradeService service, Trade trade, CommandChain next) {
        super(service, trade, next);
    }

    @Override
    public boolean accept() {
        return getTrade().getAction() == TradeAction.UPDATE;
    }

    @Override
    public void check() {
        Transaction transaction = tradeService.getLatestTransaction(getTrade().getTradeId())
                .orElseThrow(() -> new NoTranactionExistException("No previous transaction exist for update action, " + getTrade()));

        if(transaction.getAction() == TradeAction.CANCEL) {
            throw new TransactionAlreadyCanceledException("Previous transaction already canceled and cannot be updated again, " + transaction);
        }

        if(transaction.getVersion() >= getTrade().getVersion()) {
            throw new TradeVersionNotCorrectException("trade version is not correct, " + getTrade());
        }

    }

    @Override
    public void updatePosition() {
        int preQuantity = undo();

        int curQuantity = getTradeQuantity();

        super.updatePosition(curQuantity + preQuantity);
    }

    @Override
    public void postAction() {

    }
}
