package com.mangobazar.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class handle exception thrown from rest controller.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(CustomException.class)
    void handleCustomException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
        log.error(ex.getMessage(), ex);
    }

    @ExceptionHandler(Exception.class)
    void handleGeneralException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.getMessage(), ex);
    }
}
