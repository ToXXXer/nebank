package com.bxrbasov.app.dto.Wallet;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class TransactionDto {
    private String transaction_type;
    private String surname_from;
    private Long amount_money;
    private LocalDateTime transaction_date;
}
