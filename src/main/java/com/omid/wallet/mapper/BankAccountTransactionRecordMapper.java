package com.omid.wallet.mapper;

import com.omid.wallet.dto.BankAccountTransactionRecordDTO;
import com.omid.wallet.dto.UserDTO;
import com.omid.wallet.entity.BankAccountTransactionRecord;
import com.omid.wallet.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankAccountTransactionRecordMapper {
    BankAccountTransactionRecord bankAccountTransactionRecordDtoToEntity (BankAccountTransactionRecordDTO bankAccountTransactionRecordDTO);
    BankAccountTransactionRecordDTO bankAccountTransactionRecordEntitytoDto (BankAccountTransactionRecord bankAccountTransactionRecord);
    List<BankAccountTransactionRecord> bankAccountTransactionRecordDtoListToEntityList (List<BankAccountTransactionRecordDTO> BankAccountTransactionRecordDTOs);
    List<BankAccountTransactionRecordDTO> bankAccountTransactionRecordEntityListToDto (List<BankAccountTransactionRecord> bankAccountTransactionRecordList);
}
