package com.bxrbasov.app.service;

import com.bxrbasov.app.dao.TransactionDao;
import com.bxrbasov.app.dao.UserDao;
import com.bxrbasov.app.dao.WalletDao;
import com.bxrbasov.app.dto.Wallet.CreateTransactionDto;
import com.bxrbasov.app.dto.Wallet.TransactionDto;
import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.entity.Transaction;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.mapper.Wallet.CreateTransactionMapper;
import com.bxrbasov.app.validator.ValidationResult;
import com.bxrbasov.app.validator.Wallet.CreateTransactionValidator;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    TransactionDao transactionDao = TransactionDao.getInstance();
    WalletDao walletDao = WalletDao.getInstance();
    UserDao userDao = UserDao.getInstance();

    private static final TransactionService instance = new TransactionService();
    public static TransactionService getInstance() {
        return instance;
    }

    public void saveTransaction(CreateTransactionDto createTransactionDto) {
        ValidationResult validationResult = new CreateTransactionValidator().isValid(createTransactionDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        transactionDao.save(new CreateTransactionMapper().map(createTransactionDto));
    }

    public List<TransactionDto> getAllByWalletId(Integer walletId) {
        List<Transaction> byWalletId = transactionDao.getByWalletId(walletId);
        List<TransactionDto> transactionDtos = byWalletId.stream().map(transaction -> buildTransactionDto(transaction)).toList();
        return transactionDtos;
    }

    private TransactionDto buildTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .transaction_date(transaction.getTransaction_date())
                .transaction_type(transaction.getTransaction_type())
                .amount_money(transaction.getAmount_money())
                .surname_from(userDao.get(walletDao.get(transaction.getWallet_id_from()).get().getUser_id()).get().getUser_surname())
                .build();
    }
}
