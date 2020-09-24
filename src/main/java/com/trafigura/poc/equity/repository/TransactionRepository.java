package com.trafigura.poc.equity.repository;

import com.trafigura.poc.equity.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by JACK YANG on 2020/9/23.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findTopByTradeIdOrderByVersionDesc(String tradeId);
}
