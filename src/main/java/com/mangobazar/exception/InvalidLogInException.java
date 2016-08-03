package com.mangobazar.exception;


public class InvalidLogInException extends CustomException{
    private static final String errorMessage = "Username/password did not match";

    public InvalidLogInException() {
        super(ErrorCodes.ERROR_INVALID_LOGIN, errorMessage);
    }
}
