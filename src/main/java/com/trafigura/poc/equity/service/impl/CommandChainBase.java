package com.trafigura.poc.equity.service.impl;

import com.trafigura.poc.equity.domain.BuySell;
import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.service.TradeService;

/**
 * Created by JACK YANG on 2020/9/23.
 */
public abstract class CommandChainBase implements CommandChain {
    protected TradeService tradeService;
    private final Trade trade;
    private final CommandChain nextCommand;

    public CommandChainBase(TradeService service, Trade t, final CommandChain next) {
        tradeService = service;
        trade = t;
        nextCommand = next;
    }

    protected Trade getTrade() {
        return trade;
    }

    protected Integer getTradeQuantity() {
        return trade.getQuantity() * (trade.getBuySell() == BuySell.BUY ? 1 : -1);
    }

    protected Integer undo() {
        return tradeService.getLatestTransaction(trade.getTradeId())
                .map(preTransaction -> preTransaction.getQuantity() * (preTransaction.getBuySell() == BuySell.BUY ? -1 : 1))
                .orElse(0);

    }

    protected void updatePosition(Integer quantity) {
        tradeService.updatePosition(getTrade().getSecurityCode(), quantity);
    }

    private void saveTrade() {
        tradeService.saveTransaction(trade);
    }



    @Override
    public boolean execute() {

        if (!accept()) {
            return nextCommand.execute();
        }
        check();
        updatePosition();
        saveTrade();
        postAction();
        return true;
    }

    public abstract boolean accept();
    public abstract void check();
    public abstract void updatePosition();
    public abstract void postAction();


}
