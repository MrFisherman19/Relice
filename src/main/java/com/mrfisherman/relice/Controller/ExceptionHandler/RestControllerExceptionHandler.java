package com.mrfisherman.relice.Controller.ExceptionHandler;

import org.hibernate.PropertyValueException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;

import static com.mrfisherman.relice.Controller.ExceptionHandler.HandlerUtil.createResponseWithMessageAndError;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Object not found for given id";
    private static final String NOT_NULL_VALUE_WAS_NULL_MESSAGE = "Not null property references null or transient value";

    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NOT_FOUND_EXCEPTION_MESSAGE)
    public HashMap<String, String> handleEntityWithIdDoesNotExist(Exception e) {
        return createResponseWithMessageAndError(NOT_FOUND_EXCEPTION_MESSAGE, e);
    }

    @ExceptionHandler({PropertyValueException.class, ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = NOT_NULL_VALUE_WAS_NULL_MESSAGE)
    public HashMap<String, String> handleNotNullPropertyReferencesNullValue(Exception e) {
        return createResponseWithMessageAndError(NOT_NULL_VALUE_WAS_NULL_MESSAGE, e);
    }

}
