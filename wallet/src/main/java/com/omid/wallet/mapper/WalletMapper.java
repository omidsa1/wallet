package com.omid.wallet.mapper;

import com.omid.wallet.dto.WalletDTO;
import com.omid.wallet.entity.WalletEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletEntity walletDtoToEntity (WalletDTO walletDTO);
    WalletDTO walletEntityToDto (WalletEntity walletEntity);
    List<WalletEntity> walletDtoListToEntityList (List<WalletDTO> walletDTOs);
    List<WalletDTO> walletEntityListToDtoList (List<WalletEntity> walletEntities);
}
