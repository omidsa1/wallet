package com.omid.wallet.repos;

import com.omid.wallet.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    boolean existsByNameIgnoreCase(String name);

}
