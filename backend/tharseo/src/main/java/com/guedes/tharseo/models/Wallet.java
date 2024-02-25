package com.guedes.tharseo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double amount;
    private Integer isActive;
    @OneToMany
    private List<UserAsset> assets = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    private User user;

    public Wallet() {
    }

    public Wallet(Long id, String description, User user) {
        this.id = id;
        this.description = description;
        this.isActive = 1;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Double getAmount() {
        return getTotalAmount();
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isactive) {
        this.isActive = isactive;
    }

    public List<UserAsset> getAssets() {
        return assets;
    }

    public void setAssets(List<UserAsset> assets) {
        this.assets = assets;
    }

    public void addUserAsset(UserAsset userAsset) {
        this.assets.add(userAsset);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", isactive=" + isActive +
                ", assets=" + assets +
                '}';
    }

    public Double getTotalAmount() {
        Double value = 0.0;
        for (UserAsset asset : this.assets) {
            value += asset.getAmmount();
        }
        return value;
    }
}
