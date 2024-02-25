package com.guedes.tharseo.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Asset asset;
    private Instant openDate;
    private Double priceOpen;
    private Instant closeDate;
    private Double priceClose;
    private Double priceTarget;
    private Double profit;
    private Double stopLoss;
    private Integer isActive;

    public Trade() {
    }

    public Trade(Long id, User user, Asset asset, Instant openDate, Double priceOpen, Instant closeDate, Double priceClose, Double priceTarget, Double profit, Double stopLoss) {
        this.id = id;
        this.user = user;
        this.asset = asset;
        this.openDate = openDate;
        this.priceOpen = priceOpen;
        this.closeDate = closeDate;
        this.priceClose = priceClose;
        this.priceTarget = priceTarget;
        this.profit = profit;
        this.stopLoss = stopLoss;
        this.isActive = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Instant getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Instant openDate) {
        this.openDate = openDate;
    }

    public Double getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(Double priceOpen) {
        this.priceOpen = priceOpen;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public Double getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(Double priceClose) {
        this.priceClose = priceClose;
    }

    public Double getPriceTarget() {
        return priceTarget;
    }

    public void setPriceTarget(Double priceTarget) {
        this.priceTarget = priceTarget;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(Double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", user=" + user +
                ", asset=" + asset +
                ", openDate=" + openDate +
                ", priceOpen=" + priceOpen +
                ", closeDate=" + closeDate +
                ", priceClose=" + priceClose +
                ", priceTarget=" + priceTarget +
                ", profit=" + profit +
                ", stopLoss=" + stopLoss +
                ", isActive=" + isActive +
                '}';
    }
}
