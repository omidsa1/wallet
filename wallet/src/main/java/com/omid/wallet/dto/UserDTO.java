package com.omid.wallet.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private  String username;

    @NotNull
    @Size(max = 20)
    private String firstName;

    @NotNull
    @Size(max = 40)
    private String lastName;

    @NotNull
    private Integer iban;

}
