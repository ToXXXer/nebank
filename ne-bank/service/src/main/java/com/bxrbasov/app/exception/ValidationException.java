package com.bxrbasov.app.exception;

import com.bxrbasov.app.validator.Error;
import lombok.Getter;
import java.util.List;

public class ValidationException extends RuntimeException{

    @Getter
    private final List<Error> errorList;
    public ValidationException(List<Error> errorList) {
        this.errorList = errorList;
    }

}
