package com.bxrbasov.app.validator.User;

import com.bxrbasov.app.dao.UserDao;
import com.bxrbasov.app.dto.User.ProfileRedactorDto;
import com.bxrbasov.app.dto.User.RegistrationUserDto;
import com.bxrbasov.app.entity.User;
import com.bxrbasov.app.validator.Error;
import com.bxrbasov.app.validator.Validation;
import com.bxrbasov.app.validator.ValidationResult;

import java.util.List;
import java.util.regex.Pattern;

public class ProfileRedactorUserValidator implements Validation<ProfileRedactorDto> {
    private static final UserDao userDao = UserDao.getInstance();
    @Override
    public ValidationResult isValid(ProfileRedactorDto entity) {
        List<User> users = userDao.getAllWithoutUserWithId(entity.getUser_id());
        var validationResult = new ValidationResult();

        switch (entity.getUser_privilege()) {
            case "corporation":
                checkInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkPhone_number(entity.getUser_phone_number(), validationResult);
                checkInvalidCharacters("email", entity.getUser_email(), validationResult);
//                checkEmail(entity.getUser_email(), validationResult);
                checkInvalidCharacters("password", entity.getUser_password(), validationResult);
                checkInvalidCharacters("corporation_name", entity.getUser_corporation_name(), validationResult);
                checkInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkInvalidCharacters("password_confirm", entity.getUser_password_confirm(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;

            case "businessman":
                checkInvalidCharacters("first_name", entity.getUser_first_name(), validationResult);
                checkInvalidCharacters("last_name", entity.getUser_last_name(), validationResult);
                checkInvalidCharacters("patronymic_name", entity.getUser_patronymic_name(), validationResult);
                checkInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkPhone_number(entity.getUser_phone_number(), validationResult);
                checkInvalidCharacters("email", entity.getUser_email(), validationResult);
//                checkEmail(entity.getUser_email(), validationResult);
                checkInvalidCharacters("password", entity.getUser_password(), validationResult);
                checkInvalidCharacters("corporation_name", entity.getUser_corporation_name(), validationResult);
                checkInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkInvalidCharacters("password_confirm", entity.getUser_password_confirm(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;

            case "private":
                checkInvalidCharacters("first_name", entity.getUser_first_name(), validationResult);
                checkInvalidCharacters("last_name", entity.getUser_last_name(), validationResult);
                checkInvalidCharacters("patronymic_name", entity.getUser_patronymic_name(), validationResult);
                checkInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkPhone_number(entity.getUser_phone_number(), validationResult);
                checkInvalidCharacters("email", entity.getUser_email(), validationResult);
//                checkEmail(entity.getUser_email(), validationResult);
                checkInvalidCharacters("password", entity.getUser_password(), validationResult);
                checkInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkInvalidCharacters("password_confirm", entity.getUser_password_confirm(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;
            case "admin":
                checkInvalidCharacters("phone_number", entity.getUser_phone_number(), validationResult);
                checkInvalidCharacters("email", entity.getUser_email(), validationResult);
                checkInvalidCharacters("password", entity.getUser_password(), validationResult);
                checkInvalidCharacters("surname", entity.getUser_surname(), validationResult);
                checkInvalidCharacters("password_confirm", entity.getUser_password_confirm(), validationResult);
                checkPasswordConfirm(entity.getUser_password(), entity.getUser_password_confirm(), validationResult);
                checkUniqueValue(users, entity, validationResult);
                return validationResult;
            default:
                validationResult.add(com.bxrbasov.app.validator.Error.of("invalid privilege", entity.getUser_privilege()));
                return validationResult;
        }
    }

    private void checkInvalidCharacters(String type, String value, ValidationResult validationResult) {
        if(!value.equals(null) && !value.equals("")) {
            Validation.checkSize(type, value, validationResult);
            for (Character c : value.toCharArray()) {
                if (!(Character.isLetterOrDigit(c) || CHARACTERS.contains(c))) {
                    validationResult.add(com.bxrbasov.app.validator.Error.of("invalid " + type, value));
                }
            }
        }
    }

    private void checkPasswordConfirm(String password, String password_confirm, ValidationResult validationResult) {
        if (!password.equals(password_confirm)) {
            validationResult.add(Error.of("invalid password confirm", "password is " + password + ", but password_confirm is " + password_confirm));
        }
    }

    private void checkPhone_number(String phone_number, ValidationResult validationResult) {
        String regex = "\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}";
        if(!Pattern.matches(regex, phone_number)) {
            validationResult.add(Error.of("invalid phone number", "this phone number is not correct - use format (XXX) XXX-XX-XX"));
        }
    }

//    private void checkEmail(String email, ValidationResult validationResult) {
//        String regex = "[a-zA-Z]\\w*@(mail | gmail)\\.(com | ru)";
//        if(!Pattern.matches(regex, email)) {
//            validationResult.add(Error.of("invalid email", "this email is not correct"));
//        }
//    }

    private void checkUniqueValue(List<User> users, ProfileRedactorDto entity, ValidationResult validationResult) {
        if(users.stream().map(user -> user.getUser_email()).anyMatch(entity.getUser_email()::equalsIgnoreCase)) {
            validationResult.add(Error.of("invalid email", "this email already used"));
        }
        if(users.stream().map(user -> user.getUser_phone_number()).anyMatch(entity.getUser_phone_number()::equalsIgnoreCase)) {
            validationResult.add(Error.of("invalid email", "this email already used"));
        }
        if(users.stream().map(user -> user.getUser_surname()).anyMatch(entity.getUser_surname()::equalsIgnoreCase)) {
            validationResult.add(Error.of("invalid email", "this email already used"));
        }
    }
}
