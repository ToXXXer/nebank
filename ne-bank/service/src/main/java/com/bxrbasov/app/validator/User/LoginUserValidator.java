package com.bxrbasov.app.validator.User;

import com.bxrbasov.app.dto.User.LoginUserDto;
import com.bxrbasov.app.validator.Validation;
import com.bxrbasov.app.validator.ValidationResult;

public class LoginUserValidator implements Validation<LoginUserDto> {
    @Override
    public ValidationResult isValid(LoginUserDto entity) {
        ValidationResult validationResult = new ValidationResult();

        Validation.checkNullOrEmptyOrInvalidCharacters("surname", entity.getUser_surname(), validationResult);
        Validation.checkNullOrEmptyOrInvalidCharacters("password", entity.getUser_password(), validationResult);

        return validationResult;
    }

}
