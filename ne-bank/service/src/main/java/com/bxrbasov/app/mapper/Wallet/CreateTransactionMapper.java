package com.bxrbasov.app.mapper.Wallet;

import com.bxrbasov.app.dto.Wallet.CreateTransactionDto;
import com.bxrbasov.app.entity.Transaction;
import com.bxrbasov.app.mapper.Mapper;

public class CreateTransactionMapper implements Mapper<CreateTransactionDto, Transaction> {
    @Override
    public Transaction map(CreateTransactionDto key) {
        return Transaction.builder()
                .amount_money(key.getAmount_money())
                .transaction_type(key.getTransaction_type())
                .wallet_id_from(key.getWallet_id_from())
                .wallet_id_to(key.getWallet_id_to())
                .build();
    }
}
