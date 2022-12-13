package com.omid.wallet.rest;

import com.omid.wallet.dto.UserDTO;
import com.omid.wallet.entity.UserEntity;
import com.omid.wallet.mapper.UserMapper;
import com.omid.wallet.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserResource(final UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userMapper.userEntityListToDto(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid  UserDTO userDTO) {
        UserEntity userEntity = userMapper.userDtoToEntity(userDTO);
        return new ResponseEntity<>(userMapper.userEntitytoDto(userService.save(userMapper.userDtoToEntity(userDTO))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
            @RequestBody @Valid UserDTO userDTO) {
        userService.update(userMapper.userDtoToEntity(userDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody UserEntity userDocument) {
        userService.signIn(userDocument);
    }
}
