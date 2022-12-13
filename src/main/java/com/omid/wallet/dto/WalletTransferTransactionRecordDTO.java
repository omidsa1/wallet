package com.omid.wallet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class WalletTransferTransactionRecordDTO {

    private Long id;

    @NotNull
    private Long sourceWalletId;

    @NotNull
    private Long destinationWalletId;

    @NotNull
    private Long amount;

}
