package com.omid.wallet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class WalletTransferTransactionDTO {

    private Long id;

    @NotNull
    private Long sourceWalletId;

    @Size(max = 255)
    private String destinationWalletId;

    @NotNull
    @Size(max = 255)
    private String amount;

}
