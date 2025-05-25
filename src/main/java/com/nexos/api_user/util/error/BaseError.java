package com.nexos.api_user.util.error;

import lombok.Getter;

@Getter
public abstract class BaseError extends RuntimeException {
    private final ErrorStatus status;
    private final String message;

    BaseError(ErrorStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
