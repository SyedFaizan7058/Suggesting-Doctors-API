package com.nit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

//    Explanation:
//    ex.getBindingResult().getFieldErrors(): Gets the list of field errors.
//    .map(...): Maps each field error to a simple string with the field name, class name, and error message.
//    .findFirst().orElse("Validation error"): Returns the first error message or a default message if none are found.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "Field '" + fieldError.getField() + "' in class '"
                        + fieldError.getObjectName() + "' - " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setTimestamp(LocalDateTime.now());
        exceptionInfo.setStatus(String.valueOf(ex.getStatusCode()));
        exceptionInfo.setMessage(errorMessage);

        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }
}
