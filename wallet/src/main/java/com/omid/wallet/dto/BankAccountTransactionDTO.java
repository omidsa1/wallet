package com.omid.wallet.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class BankAccountTransactionDTO {

    private Long id;

    @Size(max = 255)
    private String walletId;

    @NotNull
    private Boolean deposit;

    @Size(max = 255)
    private String amount;

}
