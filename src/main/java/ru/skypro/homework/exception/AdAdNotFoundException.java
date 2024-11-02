package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdAdNotFoundException extends RuntimeException {
    public AdAdNotFoundException(String message) {
        super(message);
    }

    public AdAdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdAdNotFoundException(Throwable cause) {
        super(cause);
    }

    public AdAdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AdAdNotFoundException() {
    }
}
