package com.omid.wallet.service;

import com.omid.wallet.entity.WalletEntity;
import com.omid.wallet.repos.UserRepository;
import com.omid.wallet.repos.WalletRepository;
import com.omid.wallet.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletServiceImpl(final WalletRepository walletRepository,
                             final UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public List<WalletEntity> findAll() {
        final List<WalletEntity> wallets = walletRepository.findAll(Sort.by("id"));
        return new ArrayList<>(wallets);
    }

    public WalletEntity get(final Long id) {
        return walletRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Long create(WalletEntity walletEntity) {
        final WalletEntity wallet = new WalletEntity();
        return walletRepository.save(wallet).getId();
    }

    public void update(WalletEntity walletEntity) {
        final WalletEntity wallet = walletRepository.findById(walletEntity.getId())
                .orElseThrow(NotFoundException::new);
        walletRepository.save(wallet);
    }

    public void delete(final Long id) {
        walletRepository.deleteById(id);
    }



//    private WalletDTO mapToDTO(WalletEntity wallet, WalletDTO walletDTO) {
//        walletDTO.setId(wallet.getId());
//        walletDTO.setName(wallet.getName());
//        walletDTO.setBalance(wallet.getBalance());
//        walletDTO.setActive(wallet.getActive());
//        walletDTO.setUser(wallet.getUser() == null ? null : wallet.getUser().getId());
//        return walletDTO;
//    }

//    private WalletEntity mapToEntity(WalletDTO walletDTO, WalletEntity wallet) {
//        wallet.setName(walletDTO.getName());
//        wallet.setBalance(walletDTO.getBalance());
//        wallet.setActive(walletDTO.getActive());
//        final UserEntity user = walletDTO.getUser() == null ? null : userRepository.findById(walletDTO.getUser())
//                .orElseThrow(() -> new NotFoundException("user not found"));
//        wallet.setUser(user);
//        return wallet;
//    }

    public boolean nameExists(String name) {
        return walletRepository.existsByNameIgnoreCase(name);
    }

}
