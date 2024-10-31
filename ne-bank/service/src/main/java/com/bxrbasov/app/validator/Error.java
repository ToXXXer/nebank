package com.bxrbasov.app.validator;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class Error {
    private String code;
    private String message;
}
