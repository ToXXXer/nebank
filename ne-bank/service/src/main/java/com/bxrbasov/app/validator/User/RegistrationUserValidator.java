package com.bxrbasov.app.validator.User;

import com.bxrbasov.app.dao.UserDao;
import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.validator.Error;
import com.bxrbasov.app.validator.Validation;
import com.bxrbasov.app.validator.ValidationResult;
import java.util.List;
import java.util.regex.Pattern;

public class RegistrationUserValidator implements Validation<RegistrationUserDto> {
    private static final UserDao userDao = UserDao.getInstance();
    @Override
    public ValidationResult isValid(RegistrationUserDto entity) {
        List<User> users = userDao.getAll();
        var validationResult = new ValidationResult();

        switch (entity.getUser_privilege()) {
            case "corporation":
                Validation.checkNullOrEmptyOrInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkPhone_number(entity.getUser_phone_number(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("email", entity.getUser_email(), validationResult);
//                checkEmail(entity.getUser_email(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("password", entity.getUser_password(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("corporation_name", entity.getUser_corporation_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;

            case "businessman":
                Validation.checkNullOrEmptyOrInvalidCharacters("first_name", entity.getUser_first_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("last_name", entity.getUser_last_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("patronymic_name", entity.getUser_patronymic_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkPhone_number(entity.getUser_phone_number(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("email", entity.getUser_email(), validationResult);
//                checkEmail(entity.getUser_email(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("password", entity.getUser_password(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("corporation_name", entity.getUser_corporation_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;

            case "private":
                Validation.checkNullOrEmptyOrInvalidCharacters("first_name", entity.getUser_first_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("last_name", entity.getUser_last_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("patronymic_name", entity.getUser_patronymic_name(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkPhone_number(entity.getUser_phone_number(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("email", entity.getUser_email(), validationResult);
//                checkEmail(entity.getUser_email(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("password", entity.getUser_password(), validationResult);
                Validation.checkNullOrEmptyOrInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;
            default:
                validationResult.add(com.bxrbasov.app.validator.Error.of("invalid privilege", entity.getUser_privilege()));
                return validationResult;
        }
    }

    private void checkPasswordConfirm(String password, String password_confirm, ValidationResult validationResult) {
        if (!password.equals(password_confirm)) {
            validationResult.add(Error.of("invalid password confirm", "password is " + password + ", but password_confirm is " + password_confirm));
        }
    }

    private void checkUniqueValue(List<User> users, RegistrationUserDto entity, ValidationResult validationResult) {
        if(users.stream().map(user -> user.getUser_email()).anyMatch(entity.getUser_email()::equalsIgnoreCase)) {
            validationResult.add(Error.of("invalid email", "this email already used"));
        }
        if(users.stream().map(user -> user.getUser_phone_number()).anyMatch(entity.getUser_phone_number()::equalsIgnoreCase)) {
            validationResult.add(Error.of("invalid phone number", "this phone number already used"));
        }
        if(users.stream().map(user -> user.getUser_surname()).anyMatch(entity.getUser_surname()::equalsIgnoreCase)) {
            validationResult.add(Error.of("invalid surname", "this surname already used"));
        }
    }

    private void checkPhone_number(String phone_number, ValidationResult validationResult) {
        String regex = "\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}";
        if(!Pattern.matches(regex, phone_number)) {
            validationResult.add(Error.of("invalid phone number", "this phone number is not correct - use format (XXX) XXX-XX-XX"));
        }
    }

//    private void checkEmail(String email, ValidationResult validationResult) {
//        String regex = "[a-zA-Z]\\w*@(mail|gmail)\\.(com | ru)";
//        if(!Pattern.matches(regex, email)) {
//            validationResult.add(Error.of("invalid email", "this email is not correct"));
//        }
//    }
}
