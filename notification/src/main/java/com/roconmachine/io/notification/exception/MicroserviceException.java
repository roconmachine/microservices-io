package com.roconmachine.io.notification.exception;

import com.roconmachine.io.notification.exception.models.ApiError;
import io.swagger.annotations.Api;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MicroserviceException extends GlobalExceptionHandler{
    Logger logger = LoggerFactory.getLogger("MicroserviceException");
    @ExceptionHandler(MessagingException.class)
    protected ResponseEntity<ApiError> handleException(MessagingException ex) {
        ApiError apiError= new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        ex.printStackTrace();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
