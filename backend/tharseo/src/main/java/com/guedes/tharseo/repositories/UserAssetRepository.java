package com.guedes.tharseo.repositories;

import com.guedes.tharseo.models.Asset;
import com.guedes.tharseo.models.UserAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAssetRepository extends JpaRepository<UserAsset, Long> {


}
