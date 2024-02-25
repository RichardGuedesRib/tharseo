package com.guedes.tharseo.repositories;

import com.guedes.tharseo.models.Trade;
import com.guedes.tharseo.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
