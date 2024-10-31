package com.bxrbasov.app.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Wallet {
    private Integer wallet_id;
    private String wallet_name;
    private String wallet_purpose;
    private String wallet_comment;
    private Integer user_id;
    private Long amount_money;
}
