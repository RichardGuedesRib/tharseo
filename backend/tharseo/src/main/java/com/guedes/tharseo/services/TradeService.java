package com.guedes.tharseo.services;

import com.guedes.tharseo.models.Trade;
import com.guedes.tharseo.repositories.TradeRepository;
import com.guedes.tharseo.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;


    public void insertTrade(Trade trade) {
        if (trade != null) {
            trade.setIsActive(1);
            tradeRepository.save(trade);
        }
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade findById(Long id) {
        Optional<Trade> trade = tradeRepository.findById(id);
        return trade.orElse(null);
    }

    public Trade updateTrade(Long id, Trade trade) {
        Trade oldTrade = findById(id);
        if (oldTrade == null) {
            return null;
        }
        if (trade.getUser() != null) {
            oldTrade.setUser(trade.getUser());
        }
        if (trade.getAsset() != null) {
            oldTrade.setAsset(trade.getAsset());
        }
        if (trade.getOpenDate() != null) {
            oldTrade.setOpenDate(trade.getOpenDate());
        }
        if (trade.getPriceOpen() != null) {
            oldTrade.setPriceOpen(trade.getPriceOpen());
        }
        if (trade.getCloseDate() != null) {
            oldTrade.setCloseDate(trade.getCloseDate());
        }
        if (trade.getPriceClose() != null) {
            oldTrade.setPriceClose(trade.getPriceClose());
        }
        if (trade.getPriceClose() != null) {
            oldTrade.setPriceTarget(trade.getPriceClose());
        }
        if (trade.getProfit() != null) {
            oldTrade.setProfit(trade.getProfit());
        }
        if (trade.getStopLoss() != null) {
            oldTrade.setStopLoss(trade.getStopLoss());
        }
        if (trade.getIsActive() != null) {
            oldTrade.setIsActive(trade.getIsActive());
        }
        return tradeRepository.save(oldTrade);
    }

    public void DeleteTradeById(Long id) {
        Trade trade = findById(id);
        trade.setIsActive(0);
        updateTrade(id, trade);
    }

}
