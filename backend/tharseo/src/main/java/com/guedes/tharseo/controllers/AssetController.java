package com.guedes.tharseo.controllers;

import com.guedes.tharseo.models.Asset;
import com.guedes.tharseo.models.User;
import com.guedes.tharseo.services.AssetService;
import com.guedes.tharseo.services.UserService;
import com.guedes.tharseo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/assets")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Asset> assets = assetService.findAll();
        return ResponseEntity.ok().body(assets);
    }

    @PostMapping
    public ResponseEntity<?> insertAsset(@RequestBody Asset asset) {
        if (asset == null) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body("Asset is null! Check the asset passed as a parameter");
        } else {

            if (!Validator.validateString(asset.getAcronym())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acronym asset is invalid");
            } else {
                Asset checkAsset = assetService.findByAcronym(asset.getAcronym());
                if (checkAsset != null) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Asset already registered in the system");
                }
            }
            assetService.insertAsset(asset);
            return ResponseEntity.status(HttpStatus.CREATED).body(asset);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAsset(@RequestBody Asset asset, @PathVariable Long id) {
        Asset oldAsset = assetService.findById(id);
        if (oldAsset != null) {
            assetService.updateAsset(oldAsset.getId(), asset);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Asset updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asset id: " + id + " not found!");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable Long id) {
        Asset asset = assetService.findById(id);
        if (asset == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asset id: " + id + " not found");
        } else {
            assetService.DeleteAssetById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Asset id: " + id + " deleted");
        }
    }

}
