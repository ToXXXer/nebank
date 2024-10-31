package com.bxrbasov.app.dto.User;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoginUserDto {
    private String user_surname;
    private String user_password;
}
