package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InCorrectPasswordException extends RuntimeException {
    public InCorrectPasswordException(String message) {
        super(message);
    }
}
