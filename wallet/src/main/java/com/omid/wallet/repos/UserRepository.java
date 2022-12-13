package com.omid.wallet.repos;

import com.omid.wallet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByIban(Integer iban);

    Optional<UserEntity> findByUsername(String username);
}
