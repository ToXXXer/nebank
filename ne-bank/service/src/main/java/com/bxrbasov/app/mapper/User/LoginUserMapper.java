package com.bxrbasov.app.mapper.User;

import com.bxrbasov.app.dto.User.LoginUserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.mapper.Mapper;

public class LoginUserMapper implements Mapper<LoginUserDto, User> {
    @Override
    public User map(LoginUserDto key) {
        return User.builder()
                .user_surname(key.getUser_surname())
                .user_password(key.getUser_password())
                .build();
    }
}
