package com.omid.wallet.repos;

import com.omid.wallet.entity.WalletTransferTransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WalletTransferTransactionRecordRepository extends JpaRepository<WalletTransferTransactionRecord, Long> {

}
