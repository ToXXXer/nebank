package com.bxrbasov.app.validator.Wallet;

import com.bxrbasov.app.dto.Wallet.WalletDto;
import com.bxrbasov.app.validator.Error;
import com.bxrbasov.app.validator.Validation;
import com.bxrbasov.app.validator.ValidationResult;

public class DeleteWalletValidator implements Validation<WalletDto> {
    @Override
    public ValidationResult isValid(WalletDto entity) {
        ValidationResult validationResult = new ValidationResult();
        if(entity.getAmount_money() > 0) {
            validationResult.add(Error.of("invalid amount money", "amount_money equal " +
                                                                  entity.getAmount_money() + ", but should be equal to 0"));
        }
        return validationResult;
    }
}
