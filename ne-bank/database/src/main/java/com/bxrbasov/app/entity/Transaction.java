package com.bxrbasov.app.entity;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class Transaction {
    private Integer transaction_id;
    private String transaction_type;
    private Integer wallet_id_from;
    private Integer wallet_id_to;
    private Long amount_money;
    private LocalDateTime transaction_date;
}
