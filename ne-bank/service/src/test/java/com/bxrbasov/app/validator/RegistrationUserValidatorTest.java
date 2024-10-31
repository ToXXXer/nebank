package com.bxrbasov.app.validator;

import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.validator.User.RegistrationUserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RegistrationUserValidatorTest {

    @Test
    void isValidSuccess() {
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

        ValidationResult valid = new RegistrationUserValidator().isValid(userDto);

        Assertions.assertEquals(valid.getErrorList().size(), 0);
    }

    @Test
    void isValidFailurePrivilege() {
        RegistrationUserDto userDto = RegistrationUserDto.builder()
                .user_surname("user-1")
                .user_privilege("admin")
                .user_last_name("last_name-1")
                .user_first_name("first_name-1")
                .user_patronymic_name("patronymic_name-1")
                .user_phone_number("(999)999-99-99")
                .user_email("user@gmail.com")
                .user_corporation_name(null)
                .user_password("user-1")
                .user_password_confirm("user-1")
                .build();

        ValidationResult valid = new RegistrationUserValidator().isValid(userDto);

        Assertions.assertEquals(valid.getErrorList().size(), 1);
        Assertions.assertEquals(valid.getErrorList().get(0).getCode(), "invalid privilege");
    }

    @Test
    void isValid() {
        RegistrationUserDto userDto = RegistrationUserDto.builder()
                .user_surname("user-1#")
                .user_privilege("businessman")
                .user_last_name("last_name-1#")
                .user_first_name("first_name-1#")
                .user_patronymic_name("patronymic_name-1#")
                .user_phone_number("(999)999-99-99#")
                .user_email("user@gmail.com#")
                .user_corporation_name("Company-1#")
                .user_password("user-1#")
                .user_password_confirm("user-1#")
                .build();

        ValidationResult valid = new RegistrationUserValidator().isValid(userDto);

        Assertions.assertEquals(valid.getErrorList().size(), 8);
    }

    @Test
    void isValidFailureEmailAndSurname() {
        RegistrationUserDto userDto = RegistrationUserDto.builder()
                .user_surname("user-1#")
                .user_privilege("private")
                .user_last_name("last_name-1")
                .user_first_name("first_name-1")
                .user_patronymic_name("patronymic_name-1")
                .user_phone_number("(999)999-99-99")
                .user_email("user@gmail.com#")
                .user_corporation_name(null)
                .user_password("user-1")
                .user_password_confirm("user-1")
                .build();

        ValidationResult valid = new RegistrationUserValidator().isValid(userDto);

        Assertions.assertEquals(valid.getErrorList().size(), 2);
        Assertions.assertTrue(valid.getErrorList().stream().map(error -> error.getCode()).toList().containsAll(List.of("invalid email", "invalid surname")));
    }

}