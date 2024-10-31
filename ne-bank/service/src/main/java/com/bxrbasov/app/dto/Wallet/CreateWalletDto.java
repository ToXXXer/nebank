package com.bxrbasov.app.dto.Wallet;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateWalletDto {
    private String wallet_name;
    private String wallet_purpose;
    private String wallet_comment;
    private Integer user_id;
}
