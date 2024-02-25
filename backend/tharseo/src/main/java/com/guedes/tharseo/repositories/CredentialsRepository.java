package com.guedes.tharseo.repositories;

import com.guedes.tharseo.models.Credentials;
import com.guedes.tharseo.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
}
