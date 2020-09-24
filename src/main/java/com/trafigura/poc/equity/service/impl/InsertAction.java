package com.trafigura.poc.equity.service.impl;

import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.service.TradeService;


/**
 * Created by JACK YANG on 2020/9/23.
 */
public class InsertAction extends CommandChainBase {

    public InsertAction(TradeService service, Trade trade, CommandChain next) {
        super(service, trade, next);
    }

    @Override
    public boolean accept() {
        return getTrade().getAction() == TradeAction.INSERT;
    }

    @Override
    public void check() {
        tradeService.getLatestTransaction(getTrade().getTradeId())
                .ifPresent(
                        transaction -> {
                            throw new RuntimeException("Insert should be the first transaction: "
                                    + getTrade() + " Transaction already existed " + transaction);
                        });


    }

    @Override
    public void updatePosition() {
        super.updatePosition(getTradeQuantity());
    }

    @Override
    public void postAction() {

    }


}
