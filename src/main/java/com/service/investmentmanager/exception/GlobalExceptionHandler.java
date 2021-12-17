package com.service.investmentmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccountNumberNotFoundException.class)
    public ResponseEntity<?> handleAccountNumberNotFoundException(AccountNumberNotFoundException exception) {

        GlobalExceptionResponse exceptionResponse = createGlobalExceptionResponse(404, "Account number not found", exception.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception) {

        GlobalExceptionResponse exceptionResponse = createGlobalExceptionResponse(500, "Internal server error", exception.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GlobalExceptionResponse createGlobalExceptionResponse(int code, String message, String innerMessage) {

        return GlobalExceptionResponse.builder().code(code).message(message).innerMessage(innerMessage)
                .timestamp(LocalDateTime.now().toString()).build();
    }
}