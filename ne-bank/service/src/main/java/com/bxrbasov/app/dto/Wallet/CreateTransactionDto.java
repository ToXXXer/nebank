package com.bxrbasov.app.dto.Wallet;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateTransactionDto {
    private String transaction_type;
    private Integer wallet_id_from;
    private Integer wallet_id_to;
    private Long amount_money;
}
