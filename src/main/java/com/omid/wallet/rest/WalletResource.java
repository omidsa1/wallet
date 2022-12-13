package com.omid.wallet.rest;

import com.omid.wallet.dto.BankAccountTransactionRecordDTO;
import com.omid.wallet.dto.WalletDTO;
import com.omid.wallet.mapper.BankAccountTransactionRecordMapper;
import com.omid.wallet.mapper.WalletMapper;
import com.omid.wallet.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletResource {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    private final BankAccountTransactionRecordMapper bankAccountTransactionRecordMapper;


    public WalletResource(WalletService walletService, WalletMapper walletMapper, BankAccountTransactionRecordMapper bankAccountTransactionRecordMapper) {
        this.walletService = walletService;
        this.walletMapper = walletMapper;
        this.bankAccountTransactionRecordMapper = bankAccountTransactionRecordMapper;
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets() {
        return ResponseEntity.ok(walletMapper.walletEntityListToDtoList(walletService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> getWallet(@PathVariable final Long id) {
        return ResponseEntity.ok(walletMapper.walletEntityToDto(walletService.get(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> createWallet(@RequestBody @Valid final WalletDTO walletDTO) {
        return new ResponseEntity<>(walletService.create(walletMapper.walletDtoToEntity(walletDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWallet(@PathVariable final Long id,
            @RequestBody @Valid final WalletDTO walletDTO) {
        walletService.update(walletMapper.walletDtoToEntity(walletDTO));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteWallet(@PathVariable final Long id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

    List<BankAccountTransactionRecordDTO> listAllBankAccountTransactionRecordsInDescendingDateCreatedOrder(final Long id) {
        return bankAccountTransactionRecordMapper.bankAccountTransactionRecordEntityListToDto(walletService.listAllBankAccountTransactionRecordsInDescendingDateCreatedOrder(id));
    }

    @PostMapping("/{wallet_id}/deposit/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deposit(@PathVariable final Long wallet_id, @PathVariable Long amount) {
        walletService.depositAmountFromBankAccount(wallet_id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{wallet_id}/withdraw/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> withdraw(@PathVariable final Long wallet_id, @PathVariable Long amount) {
        walletService.withDrawAmountToBankAccount(wallet_id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{wallet_id}/transfer/{amount}/{destination_wallet_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> transfer(@PathVariable Long wallet_id, @PathVariable Long amount, @PathVariable Long destination_wallet_id) {
        walletService.transfer(wallet_id, amount, destination_wallet_id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{wallet_id}/activate")
    public void activateWallet(@PathVariable Long wallet_id) {
        walletService.activateWallet(wallet_id);
    }

   @PutMapping("/{wallet_id}/deactive")
    public void deactivateWallet(@PathVariable Long wallet_id) {
        walletService.deactivateWallet(wallet_id);
    }

}
