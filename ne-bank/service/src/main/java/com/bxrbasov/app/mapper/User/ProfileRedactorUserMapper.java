package com.bxrbasov.app.mapper.User;

import com.bxrbasov.app.dto.User.ProfileRedactorDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.mapper.Mapper;

public class ProfileRedactorUserMapper implements Mapper<ProfileRedactorDto, User> {
    @Override
    public User map(ProfileRedactorDto key) {
        return User.builder()
                .user_id(key.getUser_id())
                .user_surname(key.getUser_surname())
                .user_password(key.getUser_password())
                .user_phone_number(key.getUser_phone_number())
                .user_email(key.getUser_email())
                .user_privilege(key.getUser_privilege())
                .user_last_name(key.getUser_last_name())
                .user_first_name(key.getUser_first_name())
                .user_patronymic_name(key.getUser_patronymic_name())
                .user_corporation_name(key.getUser_corporation_name())
                .user_image(key.getUser_image())
                .build();
    }
}
