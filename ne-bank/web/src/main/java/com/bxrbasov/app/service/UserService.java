package com.bxrbasov.app.service;

import com.bxrbasov.app.dao.UserDao;
import com.bxrbasov.app.dto.User.LoginUserDto;
import com.bxrbasov.app.dto.User.ProfileRedactorDto;
import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.exception.ValidationException;
import com.bxrbasov.app.mapper.User.LoginUserMapper;
import com.bxrbasov.app.mapper.User.ProfileRedactorUserMapper;
import com.bxrbasov.app.mapper.User.RegistrationUserMapper;
import com.bxrbasov.app.validator.*;
import com.bxrbasov.app.validator.Error;
import com.bxrbasov.app.validator.User.LoginUserValidator;
import com.bxrbasov.app.validator.User.ProfileRedactorUserValidator;
import com.bxrbasov.app.validator.User.RegistrationUserValidator;
import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    public UserDto registrationUser(RegistrationUserDto registrationUserDto) {
        ValidationResult validationResult = new RegistrationUserValidator().isValid(registrationUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        User user = new RegistrationUserMapper().map(registrationUserDto);
        User newUser = userDao.save(user).get();
        return buildUserDto(newUser);
    }

    public UserDto loginUser(LoginUserDto loginUserDto) {
        ValidationResult validationResult = new LoginUserValidator().isValid(loginUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        User user = new LoginUserMapper().map(loginUserDto);
        Optional<User> newUser = userDao.getLoginUser(user);
        if(newUser.isPresent()) {
            return buildUserDto(newUser.get());
        } else {
            validationResult.add(Error.of("invalid user", "user is not found"));
            throw new ValidationException(validationResult.getErrorList());
        }
    }

    public UserDto redactorUser(ProfileRedactorDto profileRedactorDto) {
        ValidationResult validationResult = new ProfileRedactorUserValidator().isValid(profileRedactorDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorList());
        }
        User user = new ProfileRedactorUserMapper().map(profileRedactorDto);
        Optional<User> newUser = null;
        if (user.getUser_password().equals("")) {
            newUser = userDao.redactUserWithoutPassword(user);
        } else {
            newUser = userDao.redactUser(user);
        }
        return buildUserDto(newUser.get());
    }

    public UserDto getUserBySurname(String user_surname) {
        User userBySurname = userDao.getUserBySurname(user_surname);
        return buildUserDto(userBySurname);
    }

    public UserDto buildUserDto(User newUser) {
        return UserDto.builder()
                .user_id(newUser.getUser_id())
                .user_surname(newUser.getUser_surname())
                .user_phone_number(newUser.getUser_phone_number())
                .user_email(newUser.getUser_email())
                .user_privilege(newUser.getUser_privilege())
                .user_corporation_name(newUser.getUser_corporation_name())
                .user_last_name(newUser.getUser_last_name())
                .user_first_name(newUser.getUser_first_name())
                .user_patronymic_name(newUser.getUser_patronymic_name())
                .user_image(newUser.getUser_image())
                .build();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
