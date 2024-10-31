package com.bxrbasov.app.validator.Wallet;

import com.bxrbasov.app.dto.Wallet.CreateWalletDto;
import com.bxrbasov.app.validator.Validation;
import com.bxrbasov.app.validator.ValidationResult;

public class CreateWalletValidator implements Validation<CreateWalletDto> {
    @Override
    public ValidationResult isValid(CreateWalletDto entity) {
        ValidationResult validationResult = new ValidationResult();
        checkInvalidCharacters("wallet comment", entity.getWallet_comment(), validationResult);
        checkInvalidCharacters("wallet purpose", entity.getWallet_purpose(), validationResult);
        Validation.checkSize("wallet purpose", entity.getWallet_purpose(), validationResult);
        checkInvalidCharacters("wallet name", entity.getWallet_name(), validationResult);
        Validation.checkSize("wallet name", entity.getWallet_name(), validationResult);
        return validationResult;
    }

    private void checkInvalidCharacters(String type, String value, ValidationResult validationResult) {
        if(!value.equals(null) && !value.equals("")) {
            for (Character c : value.toCharArray()) {
                if (!(Character.isLetterOrDigit(c) || CHARACTERS.contains(c) || c.equals(' '))) {
                    validationResult.add(com.bxrbasov.app.validator.Error.of("invalid " + type, value));
                }
            }
        }
    }
}
