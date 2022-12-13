package com.omid.wallet.service;

import com.omid.wallet.dto.UserDTO;
import com.omid.wallet.entity.UserEntity;
import com.omid.wallet.repos.UserRepository;
import com.omid.wallet.util.NotFoundException;
import com.omid.wallet.util.WalletException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        final List<UserEntity> users = userRepository.findAll(Sort.by("id"));
        return new ArrayList<>(users);
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UserEntity save(UserEntity userEntity) {
        try {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword().trim()));
            return userRepository.save(userEntity);
        } catch (DuplicateKeyException ex) {
            throw new WalletException("Username already exists!");
        }
    }

    public void update(UserEntity userEntity) {

        if ((userEntity.getId() == null ))
            throw new WalletException("Null user id prohibited!");

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = findByUsername(username);
        if (user.getId().equals(userEntity.getId())){
            save(userEntity);
        }
        else throw new WalletException("User was not updated!");
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new WalletException("User not found!"));
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new WalletException("User not found!"));
    }

    private UserDTO mapToDTO(final UserEntity user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setIban(user.getIban());
        return userDTO;
    }

    private UserEntity mapToEntity(final UserDTO userDTO, final UserEntity user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setIban(userDTO.getIban());
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found in db"));
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public void signIn(UserEntity userEntity) {
    }
}
