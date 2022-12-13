package com.omid.wallet.mapper;

import com.omid.wallet.dto.WalletDTO;
import com.omid.wallet.entity.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletEntity walletDtoToEntity (WalletDTO walletDTO);
    WalletDTO walletEntityToDto (WalletEntity walletEntity);
}
