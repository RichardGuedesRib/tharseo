package com.guedes.tharseo.controllers;

import com.guedes.tharseo.models.Asset;
import com.guedes.tharseo.models.UserAsset;
import com.guedes.tharseo.models.Wallet;
import com.guedes.tharseo.services.AssetService;
import com.guedes.tharseo.services.UserAssetService;
import com.guedes.tharseo.services.WalletService;
import com.guedes.tharseo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usersassets")
public class UserAssetController {

    @Autowired
    UserAssetService userAssetService;
    @Autowired
    AssetService assetService;
    @Autowired
    WalletService walletService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<UserAsset> userAssets = userAssetService.findAll();
        return ResponseEntity.ok().body(userAssets);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> insertUserAsset(@RequestBody UserAsset userAsset, @PathVariable Long id, @RequestParam(value = "wallet", required = true) Long walletId) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asset not Found");
        }
        Wallet wallet =walletService.findById(walletId);
        if (wallet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not Found");
        }

        if (userAsset == null) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("User Asset is null! Check the User asset passed as a parameter");
        } else {
            userAsset.setAsset(asset);
            if (!Validator.validateValue(userAsset.getQuantity().toString())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User asset quantity is invalid");
            }
        }
        userAssetService.insertUserAsset(userAsset);
        wallet.addUserAsset(userAsset);
        walletService.updateWallet(walletId, wallet);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAsset);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUserAsset(@RequestBody UserAsset userAsset, @PathVariable Long id) {
        UserAsset oldUserAsset = userAssetService.findById(id);
        if (oldUserAsset != null) {
            userAssetService.updateUserAsset(id, userAsset);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Asset updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Asset id: " + id + " not found!");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUserAsset(@PathVariable Long id) {
        UserAsset userAsset = userAssetService.findById(id);
        if (userAsset == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Asset id: " + id + " not found");
        } else {
            userAssetService.deleteUserAssetById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Asset id: " + id + " deleted");
        }
    }

}
