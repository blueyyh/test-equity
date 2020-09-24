package com.trafigura.poc.equity.controller;

import com.trafigura.poc.equity.domain.BuySell;
import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.service.TradeService;
import com.trafigura.poc.equity.service.impl.CommandChain;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by JACK YANG on 2020/9/24.
 */
@WebMvcTest
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @BeforeEach
    public void init() {
        when(tradeService.commandChain(anyObject())).thenReturn(() -> true);
    }

    @Test
    public void transactionAction_command_OK() throws Exception {
        Trade trade = new Trade("1", 1, "REL", 50, TradeAction.INSERT, BuySell.BUY);
        when(tradeService.commandChain(eq(trade))).thenReturn(() -> true);
        mockMvc.perform(post("/trade/action")
                .content("{\"tradeId\":\"1\",\"version\":\"1\",\"securityCode\":\"REL\",\"quantity\":\"50\",\"action\":\"INSERT\",\"buySell\":\"BUY\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void transactionAction_command_false() throws Exception {
        Trade trade = new Trade("1", 1, "REL", 50, TradeAction.INSERT, BuySell.BUY);
        when(tradeService.commandChain(eq(trade))).thenReturn(() -> false);
        mockMvc.perform(post("/trade/action")
                .content("{\"tradeId\":\"1\",\"version\":\"1\",\"securityCode\":\"REL\",\"quantity\":\"50\",\"action\":\"INSERT\",\"buySell\":\"BUY\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}