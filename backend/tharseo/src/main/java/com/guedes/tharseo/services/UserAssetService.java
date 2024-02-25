package com.guedes.tharseo.services;

import com.guedes.tharseo.models.UserAsset;
import com.guedes.tharseo.repositories.UserAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAssetService {

    @Autowired
    UserAssetRepository userAssetRepository;


    public void insertUserAsset(UserAsset userAsset) {
        if (userAsset != null) {
            userAsset.setIsActive(1);
            userAssetRepository.save(userAsset);
        }
    }

    public List<UserAsset> findAll() {
        return userAssetRepository.findAll();
    }

    public UserAsset findById(Long id) {
        Optional<UserAsset> userAsset = userAssetRepository.findById(id);
        return userAsset.orElse(null);
    }

    public UserAsset updateUserAsset(Long id, UserAsset userAsset) {
        UserAsset oldUserAsset = findById(id);
        if (oldUserAsset == null) {
            return null;
        }
        if (userAsset.getAsset() != null) {
            oldUserAsset.setAsset(userAsset.getAsset());
        }
        if (userAsset.getQuantity() != null) {
            oldUserAsset.setQuantity(userAsset.getQuantity());
        }
        if (userAsset.getIsActive() != null) {
            oldUserAsset.setIsActive(userAsset.getIsActive());
        }
        return userAssetRepository.save(oldUserAsset);
    }

    public void deleteUserAssetById(Long id) {
        UserAsset userAsset = findById(id);
        userAsset.setIsActive(0);
        updateUserAsset(userAsset.getId(), userAsset);
    }

}
