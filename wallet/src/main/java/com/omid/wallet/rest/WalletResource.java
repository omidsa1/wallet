package com.omid.wallet.rest;

import com.omid.wallet.dto.WalletDTO;
import com.omid.wallet.mapper.WalletMapper;
import com.omid.wallet.service.WalletServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletResource {

    private final WalletServiceImpl walletService;
    private final WalletMapper walletMapper;


    public WalletResource(final WalletServiceImpl walletService, WalletMapper walletMapper) {
        this.walletService = walletService;
        this.walletMapper = walletMapper;
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

}
