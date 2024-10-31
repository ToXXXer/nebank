package com.bxrbasov.app.validator;

import java.util.List;

public interface Validation<T> {
    List<Character> CHARACTERS = List.of('@', '.', '(', ')', '-', '_');

    ValidationResult isValid(T entity);

    static void checkNullOrEmptyOrInvalidCharacters(String type, String value, ValidationResult validationResult) {
        if(value.isEmpty() || value.equals(null)) {
            validationResult.add(Error.of("invalid " + type, value));
            return;
        }
        Validation.checkSize(type, value, validationResult);
        for (Character c : value.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || CHARACTERS.contains(c))) {
                validationResult.add(Error.of("invalid " + type, value));
            }
        }
    }

    static void checkSize(String type, String value, ValidationResult validationResult) {
        if(value.length() < 2 && value.length() > 32) {
            validationResult.add(Error.of("invalid " + type, value));
        }
    }
}
