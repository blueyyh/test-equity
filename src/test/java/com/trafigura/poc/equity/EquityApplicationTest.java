package com.trafigura.poc.equity;

import com.trafigura.poc.equity.domain.BuySell;
import com.trafigura.poc.equity.domain.Trade;
import com.trafigura.poc.equity.domain.TradeAction;
import com.trafigura.poc.equity.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by JACK YANG on 2020/9/24.
 */
@SpringBootTest
@AutoConfigureMockMvc
class EquityApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void clearData() {
        transactionRepository.deleteAll();
    }

    @Test
    public void insertAction_return_ok() throws Exception {

        mockMvc.perform(post("/trade/action")
                .content("{\"tradeId\":\"1\",\"version\":\"1\",\"securityCode\":\"REL\",\"quantity\":\"50\",\"action\":\"INSERT\",\"buySell\":\"BUY\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void cancelAction_not_last_version() throws Exception {
        mockMvc.perform(post("/trade/action")
                .content("{\"tradeId\":\"1\",\"version\":\"1\",\"securityCode\":\"REL\",\"quantity\":\"50\",\"action\":\"CANCEL\",\"buySell\":\"BUY\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.url").value("http://localhost/trade/action"))
                .andExpect(jsonPath("$.error").value("Cancel should NOT be the the first transaction: Trade{tradeId='1', version=1, securityCode='REL', quantity=50, action=CANCEL, buySell=BUY}"));
    }

}