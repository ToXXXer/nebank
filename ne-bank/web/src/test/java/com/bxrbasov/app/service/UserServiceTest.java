package com.bxrbasov.app.service;

import com.bxrbasov.app.dao.UserDao;
import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.mapper.User.LoginUserMapper;
import com.bxrbasov.app.mapper.User.RegistrationUserMapper;
import com.bxrbasov.app.validator.User.LoginUserValidator;
import com.bxrbasov.app.validator.User.RegistrationUserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;
    @Mock
    private RegistrationUserValidator registrationUserValidator;
    @Mock
    private RegistrationUserMapper registrationUserMapper;
    @Mock
    private LoginUserValidator loginUserValidator;
    @Mock
    private LoginUserMapper loginUserMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void registeretionSuccess() {
        RegistrationUserDto registrationUserDto = RegistrationUserDto.builder()
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
        Mockito.doReturn(user).when(registrationUserMapper).map(registrationUserDto);

        User newUser = Optional.of(User.builder()
                .user_id(1)
                .user_surname("user-1")
                .user_privilege("private")
                .user_last_name("last_name-1")
                .user_first_name("first_name-1")
                .user_patronymic_name("patronymic_name-1")
                .user_phone_number("(999)999-99-99")
                .user_email("user@gmail.com")
                .user_corporation_name(null)
                .user_password("user-1")
                .build()).get();
        Mockito.doReturn(newUser).when(userDao).save(user).get();

        UserDto userDto = UserDto.builder()
                .user_id(1)
                .user_surname("user-1")
                .user_privilege("private")
                .user_last_name("last_name-1")
                .user_first_name("first_name-1")
                .user_patronymic_name("patronymic_name-1")
                .user_phone_number("(999)999-99-99")
                .user_email("user@gmail.com")
                .user_corporation_name(null)
                .build();
        Mockito.doReturn(userDto).when(userService).buildUserDto(newUser);

        UserDto actualUserDto = userService.registrationUser(registrationUserDto);

        Assertions.assertEquals(actualUserDto.getUser_surname(), userDto.getUser_surname());
        Assertions.assertEquals(actualUserDto.getUser_privilege(), userDto.getUser_privilege());
        Assertions.assertEquals(actualUserDto.getUser_last_name(), userDto.getUser_last_name());
        Assertions.assertEquals(actualUserDto.getUser_first_name(), userDto.getUser_first_name());
        Assertions.assertEquals(actualUserDto.getUser_last_name(), userDto.getUser_last_name());
        Assertions.assertEquals(actualUserDto.getUser_patronymic_name(), userDto.getUser_patronymic_name());
        Assertions.assertEquals(actualUserDto.getUser_phone_number(), userDto.getUser_phone_number());
        Assertions.assertEquals(actualUserDto.getUser_email(), userDto.getUser_email());
        Assertions.assertEquals(actualUserDto.getUser_corporation_name(), userDto.getUser_corporation_name());
    }



}