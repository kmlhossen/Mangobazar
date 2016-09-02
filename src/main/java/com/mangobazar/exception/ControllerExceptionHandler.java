package com.mangobazar.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class handle exception thrown from rest controller.
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({UsernameNotFoundException.class, InvalidLogInException.class})
    void handleAuthenticationException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
        log.error(ex.getMessage(), ex);
    }

    @ExceptionHandler({AccessDeniedException.class})
    void handleAccessDeniedException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpStatus.FORBIDDEN.value());
        log.error(ex.getMessage(), ex);
    }

    @ExceptionHandler({CustomException.class, DataIntegrityViolationException.class})
    void handleBadRequestException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
        log.error(ex.getMessage(), ex);
    }

    @ExceptionHandler(Exception.class)
    void handleGenericlException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(ex.getMessage(), ex);
    }
}
