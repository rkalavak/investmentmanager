package com.service.investmentmanager.exception;

public class AccountNumberNotFoundException extends RuntimeException {

    public AccountNumberNotFoundException(String message) {
        super(message);
    }
}
