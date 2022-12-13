package com.omid.wallet.service;

import com.omid.wallet.entity.UserEntity;
import com.omid.wallet.entity.WalletEntity;
import com.omid.wallet.repos.WalletRepository;
import com.omid.wallet.util.NotFoundException;
import com.omid.wallet.util.WalletException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    public WalletService(final WalletRepository walletRepository,
                          final UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
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

    @Transactional
    public void transfer(Long sourceWalletId, Long destinationWalletId, Long  amount) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.findByUsername(username);

        WalletEntity sourceWallet = walletRepository.findById(sourceWalletId)
                .orElseThrow(NotFoundException::new);
        WalletEntity destinationWallet = walletRepository.findById(destinationWalletId)
                .orElseThrow(NotFoundException::new);


        if (!Objects.equals(sourceWallet.getUser().getId(), user.getId())) {
            throw new WalletException("You are not allowed to transfer from this wallet!");
        }

        if (!(sourceWallet.getActive()))throw new WalletException("Your wallet is not active");
        if(!(destinationWallet.getActive()))throw new WalletException("Destination wallet is not active");

        if (sourceWallet.getBalance() < amount) {
            throw new WalletException("Inadequate balance! Please increase your balance first and try again.");
        }

        sourceWallet.setBalance((sourceWallet.getBalance() - amount));
        destinationWallet.setBalance((destinationWallet.getBalance() + amount));

        walletRepository.save(sourceWallet);
        walletRepository.save(destinationWallet);
    }

    @Transactional
    public void increaseBalance(Long walletId, Long amount) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.findByUsername(username);

        WalletEntity wallet = walletRepository.findById(walletId)
                .orElseThrow(NotFoundException::new);

        if (!Objects.equals(wallet.getUser().getId(), user.getId())) {
            throw new WalletException("You are not allowed to increase balance of this wallet!");
        }

        wallet.setBalance((wallet.getBalance() + amount));
    }

    @Transactional
    public void withDrawAmountToBankAccount(Long walletId, Long amount) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userService.findByUsername(username);

        WalletEntity wallet = walletRepository.findById(walletId)
                .orElseThrow(NotFoundException::new);


        if (!Objects.equals(wallet.getUser().getId(), user.getId())) {
            throw new WalletException("You are not allowed to withdraw amount from this wallet!");
        }
        if (wallet.getBalance() < amount) {
            throw new WalletException("Not enough balance. Please increase your balance first and try again!");
        }

        wallet.setBalance((wallet.getBalance() - amount));
    }


    public boolean nameExists(String name) {
        return walletRepository.existsByNameIgnoreCase(name);
    }

}
