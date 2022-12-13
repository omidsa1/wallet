package com.omid.wallet.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class BankAccountTransactionRecordDTO {

    private Long id;

    @NotNull
    private Long walletId;

    @NotNull
    private Boolean deposit;

    @Size(max = 255)
    private String amount;

}
