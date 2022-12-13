package com.omid.wallet.repos;

import com.omid.wallet.entity.BankAccountTransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BankAccountTransactionRecordRepository extends JpaRepository<BankAccountTransactionRecord, Long> {

    List<BankAccountTransactionRecord> findAllByWalletId(Long walletId);
}
