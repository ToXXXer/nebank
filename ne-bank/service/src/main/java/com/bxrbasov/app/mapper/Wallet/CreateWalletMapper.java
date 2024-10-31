package com.bxrbasov.app.mapper.Wallet;

import com.bxrbasov.app.dto.Wallet.CreateWalletDto;
import com.bxrbasov.app.entity.Wallet;
import com.bxrbasov.app.mapper.Mapper;

public class CreateWalletMapper implements Mapper<CreateWalletDto, Wallet>  {
    @Override
    public Wallet map(CreateWalletDto key) {
        return Wallet.builder()
                .wallet_name(key.getWallet_name())
                .wallet_purpose(key.getWallet_purpose())
                .wallet_comment(key.getWallet_comment())
                .user_id(key.getUser_id())
                .build();
    }
}
