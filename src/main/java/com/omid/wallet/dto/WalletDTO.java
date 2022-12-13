package com.omid.wallet.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class WalletDTO {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    @Size(max = 255)
    private Long balance;

    private Boolean active;

    private UserDTO user;

}
