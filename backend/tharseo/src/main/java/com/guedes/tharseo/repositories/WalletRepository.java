package com.guedes.tharseo.repositories;

import com.guedes.tharseo.models.Asset;
import com.guedes.tharseo.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
