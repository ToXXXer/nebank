package com.bxrbasov.app.mapper.User;

import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.mapper.Mapper;

public class RegistrationUserMapper implements Mapper<RegistrationUserDto, User> {
    @Override
    public User map(RegistrationUserDto key) {
        return User.builder()
                .user_surname(key.getUser_surname())
                .user_password(key.getUser_password())
                .user_phone_number(key.getUser_phone_number())
                .user_email(key.getUser_email())
                .user_privilege(key.getUser_privilege())
                .user_last_name(key.getUser_last_name())
                .user_patronymic_name(key.getUser_patronymic_name())
                .user_first_name(key.getUser_first_name())
                .user_corporation_name(key.getUser_corporation_name())
                .build();
    }
}
