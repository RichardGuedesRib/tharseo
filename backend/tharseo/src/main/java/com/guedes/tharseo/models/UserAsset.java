package com.guedes.tharseo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_userasset")
public class UserAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Asset asset;
    private Double quantity;
    private Double ammount;

    private Integer isActive;

    public UserAsset() {
    }

    public UserAsset(Long id, Asset asset, Double quantity, Integer isActive) {
        this.id = id;
        this.asset = asset;
        this.quantity = quantity;
        this.isActive = isActive;
        this.ammount = quantity * asset.getMarketValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAmmount() {
        return quantity * this.asset.getMarketValue();
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
