package com.mangobazar.exception;


public class DuplicateUserException extends CustomException {
    private static final String errorMessage = "Account exist with this email address";


    public DuplicateUserException() {
        super(ErrorCodes.ERROR_USER_ALREADY_EXIST, errorMessage);
    }
}
