package com.bxrbasov.app.dto.Wallet;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class WalletDto {
    private Integer wallet_id;
    private String wallet_name;
    private String wallet_purpose;
    private String wallet_comment;
    private Long amount_money;
    private Integer user_id;
}
