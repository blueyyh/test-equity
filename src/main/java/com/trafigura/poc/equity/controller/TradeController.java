package com.trafigura.poc.equity.controller;

import com.trafigura.poc.equity.domain.BuySell;
import com.trafigura.poc.equity.domain.SecurityPosition;
import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JACK YANG on 2020/9/24.
 */
@RestController
@RequestMapping(value = "/trade")
public class TradeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeController.class);


    TradeService service;

    public TradeController(TradeService tradeService) {
        this.service = tradeService;
    }


    @PostMapping(value = "/action", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> transactionAction(@RequestBody Trade trade) {
        LOGGER.debug("received trade action {}", trade);
        if (service.commandChain(trade).execute()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/currentPosition")
    @ResponseBody
    public List<SecurityPosition> currentPosition() {
        return service.currentPosition();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDemoData() {
        //run the service to initialize some sample data

        Trade trade1 = new Trade("1", 1, "REL", 50, TradeAction.INSERT, BuySell.BUY);
        Trade trade2 = new Trade("1", 2, "REL", 100, TradeAction.UPDATE, BuySell.BUY);
        Trade trade3 = new Trade("2", 1, "ITC", 50, TradeAction.INSERT, BuySell.BUY);
        Trade trade4 = new Trade("2", 2, "ITC", 50, TradeAction.CANCEL, BuySell.SELL);
        Trade trade5 = new Trade("1", 3, "REL", 60, TradeAction.UPDATE, BuySell.SELL);
        //workaround for unit test
        if (service.commandChain(trade1) == null) return;

        service.commandChain(trade1).execute();
        service.commandChain(trade2).execute();
        service.commandChain(trade3).execute();
        service.commandChain(trade4).execute();
        service.commandChain(trade5).execute();

    }
}
