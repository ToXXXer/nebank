package com.bxrbasov.app.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Contact {
    private Integer contact_id;
    private User first_user;
    private User second_user;
}
