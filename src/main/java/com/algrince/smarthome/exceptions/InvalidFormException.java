package com.algrince.smarthome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidFormException extends RuntimeException {

    public InvalidFormException(String message) {
        super(message);
    }
}
