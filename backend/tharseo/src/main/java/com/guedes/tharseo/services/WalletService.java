package com.guedes.tharseo.services;

import com.guedes.tharseo.models.UserAsset;
import com.guedes.tharseo.models.Wallet;
import com.guedes.tharseo.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;


    public void insertWallet(Wallet wallet) {
        if (wallet != null) {
            wallet.setIsActive(1);
            walletRepository.save(wallet);
        }
    }

    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    public Wallet findById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElse(null);
    }

    public Wallet updateWallet(Long id, Wallet wallet) {
        Wallet oldWallet = findById(id);
        if (oldWallet == null) {
            return null;
        }
        if (wallet.getDescription() != null) {
            oldWallet.setDescription(wallet.getDescription());
        }
        if (wallet.getIsActive() != null) {
            oldWallet.setIsActive(wallet.getIsActive());
        }
        if(wallet.getAssets() != null){
            oldWallet.setAssets(wallet.getAssets());
        }
        return walletRepository.save(oldWallet);
    }

    public void deleteWalletById(Long id) {
        Wallet wallet = findById(id);
        wallet.setIsActive(0);
        updateWallet(wallet.getId(), wallet);
    }

    public void addAsset(Wallet wallet, UserAsset userAsset) {
        if (wallet == null || userAsset == null) {
            return;
        } else {
            wallet.addUserAsset(userAsset);
        }
    }

}
