package com.bxrbasov.app.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
    private Integer user_id;
    private String user_surname;
    private String user_password;
    private String user_phone_number;
    private String user_email;
    private String user_privilege;
    private String user_last_name;
    private String user_patronymic_name;
    private String user_first_name;
    private String user_corporation_name;
    private String user_image;
}
