package com.guedes.tharseo.repositories;

import com.guedes.tharseo.models.Asset;
import com.guedes.tharseo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findByAcronym(String acronym);
}
