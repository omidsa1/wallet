package com.omid.wallet.mapper;

import com.omid.wallet.dto.UserDTO;
import com.omid.wallet.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userDtoToEntity (UserDTO userDTO);
    UserDTO userEntitytoDto (UserEntity userEntity);
    List<UserEntity> userDtoListToEntityList (List<UserDTO> userDTOs);
    List<UserDTO> userEntityListToDto (List<UserEntity> userEntities);


}
