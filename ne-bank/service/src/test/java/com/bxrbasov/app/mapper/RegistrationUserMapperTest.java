package com.bxrbasov.app.mapper;

import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.mapper.User.RegistrationUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegistrationUserMapperTest {

    @Test
    void getUser() {
        RegistrationUserDto userDto = RegistrationUserDto.builder()
                .user_surname("user-1")
                .user_privilege("private")
                .user_last_name("last_name-1")
                .user_first_name("first_name-1")
                .user_patronymic_name("patronymic_name-1")
                .user_phone_number("(999)999-99-99")
                .user_email("user@gmail.com")
                .user_corporation_name(null)
                .user_password("user-1")
                .user_password_confirm("user-1")
                .build();

        User createdUser = new RegistrationUserMapper().map(userDto);

        User user = User.builder()
                .user_surname("user-1")
                .user_privilege("private")
                .user_last_name("last_name-1")
                .user_first_name("first_name-1")
                .user_patronymic_name("patronymic_name-1")
                .user_phone_number("(999)999-99-99")
                .user_email("user@gmail.com")
                .user_corporation_name(null)
                .user_password("user-1")
                .build();

        Assertions.assertEquals(createdUser, user);
    }

}