package com.bxrbasov.app.validator.Wallet;

import com.bxrbasov.app.dto.Wallet.CreateTransactionDto;
import com.bxrbasov.app.validator.Error;
import com.bxrbasov.app.validator.Validation;
import com.bxrbasov.app.validator.ValidationResult;

public class CreateTransactionValidator implements Validation<CreateTransactionDto> {
    @Override
    public ValidationResult isValid(CreateTransactionDto entity) {
        ValidationResult validationResult = new ValidationResult();
        if (entity.getAmount_money() <= 0) {
            validationResult.add(Error.of("invalid amount money", "amount of money should be > 0"));
        }
        return validationResult;
    }
}
