package com.omid.wallet.mapper;

import com.omid.wallet.dto.UserDTO;
import com.omid.wallet.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userDtoToEntity (UserDTO userDTO);
    UserDTO userEntitytoDto (UserEntity userEntity);

}
