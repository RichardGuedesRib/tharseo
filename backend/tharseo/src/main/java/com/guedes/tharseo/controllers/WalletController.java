package com.guedes.tharseo.controllers;

import com.guedes.tharseo.models.User;
import com.guedes.tharseo.models.UserAsset;
import com.guedes.tharseo.models.Wallet;
import com.guedes.tharseo.services.AssetService;
import com.guedes.tharseo.services.UserAssetService;
import com.guedes.tharseo.services.UserService;
import com.guedes.tharseo.services.WalletService;
import com.guedes.tharseo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;
    @Autowired
    UserAssetService userAssetService;
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Wallet> wallets = walletService.findAll();
        return ResponseEntity.ok().body(wallets);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> insertWallet(@RequestBody Wallet wallet, @PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        if (wallet == null) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Wallet is null! Check the wallet passed as a parameter");
        }
        if(user.getWallet() != null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already has a wallet");
        }
        if (!Validator.validateString(wallet.getDescription())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Description is invalid");
        }
        user.setWallet(wallet);
        wallet.setUser(user);
        walletService.insertWallet(wallet);

        return ResponseEntity.status(HttpStatus.CREATED).body(wallet);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateWallet(@RequestBody Wallet wallet, @PathVariable Long id) {
        Wallet oldWallet = walletService.findById(id);
        if (oldWallet != null) {
            walletService.updateWallet(id, wallet);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Wallet updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet id: " + id + " not found!");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteWallet(@PathVariable Long id) {
        Wallet wallet = walletService.findById(id);
        if (wallet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet Asset id: " + id + " not found");
        } else {
            walletService.deleteWalletById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Wallet id: " + id + " deleted");
        }
    }

}
