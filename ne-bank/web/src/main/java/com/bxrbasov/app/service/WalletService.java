package com.bxrbasov.app.service;

import com.bxrbasov.app.dao.WalletDao;
import com.bxrbasov.app.dto.Wallet.CreateWalletDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.entity.Wallet;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.mapper.Wallet.CreateWalletMapper;
import com.bxrbasov.app.validator.Error;
import com.bxrbasov.app.validator.ValidationResult;
import com.bxrbasov.app.validator.Wallet.CreateWalletValidator;
import com.bxrbasov.app.validator.Wallet.DeleteWalletValidator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WalletService {
    private static final WalletDao walletDao = WalletDao.getInstance();
    private static final WalletService instance = new WalletService();


    public WalletDto createWallet(CreateWalletDto createWalletDto) {
        ValidationResult validationResult = new CreateWalletValidator().isValid(createWalletDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        Wallet wallet = new CreateWalletMapper().map(createWalletDto);
        Wallet newWallet = walletDao.save(wallet).get();
        return buildWalletDto(newWallet);
    }

    public List<WalletDto> getAllByUserId(Integer user_id) {
        List<Wallet> walletList = walletDao.getAllByUserId(user_id);
        return walletList.stream().map(wallet -> buildWalletDto(wallet)).toList();
    }

    public WalletDto getWallet(Integer wallet_id) {
        Optional<Wallet> wallet = walletDao.get(wallet_id);
        if (!wallet.isPresent()) {
            throw new ValidationException(Collections.singletonList(Error.of("invalid wallet", "incorrect wallet_id")));
        } else {
            return buildWalletDto(wallet.get());
        }
    }

    public void deleteWallet(WalletDto wallet) {
        ValidationResult validationResult = new DeleteWalletValidator().isValid(wallet);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        walletDao.delete(wallet.getWallet_id());
    }

    public WalletDto addAmountMoney(Integer wallet_id, Long amountMoney) {
        Wallet wallet = walletDao.get(wallet_id).get();
        Optional<Wallet> update = walletDao.update(
                Wallet.builder()
                        .wallet_id(wallet_id)
                        .amount_money(wallet.getAmount_money() + amountMoney)
                        .wallet_name(wallet.getWallet_name())
                        .wallet_purpose(wallet.getWallet_purpose())
                        .user_id(wallet.getUser_id())
                        .wallet_comment(wallet.getWallet_comment())
                        .build()
        );
        return buildWalletDto(update.get());
    }

    public static WalletService getInstance() {
        return instance;
    }

    private WalletDto buildWalletDto(Wallet wallet) {
        return WalletDto.builder()
                .wallet_id(wallet.getWallet_id())
                .wallet_name(wallet.getWallet_name())
                .wallet_comment(wallet.getWallet_comment())
                .wallet_purpose(wallet.getWallet_purpose())
                .amount_money(wallet.getAmount_money())
                .user_id(wallet.getUser_id())
                .build();
    }
}
