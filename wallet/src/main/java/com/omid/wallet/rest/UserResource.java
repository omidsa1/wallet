package com.omid.wallet.rest;

import com.omid.wallet.dto.UserDTO;
import com.omid.wallet.mapper.UserMapper;
import com.omid.wallet.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserResource(final UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(userMapper.userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final Long id,
            @RequestBody @Valid final UserDTO userDTO) {
//

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
