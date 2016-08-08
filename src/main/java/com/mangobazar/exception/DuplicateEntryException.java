package com.mangobazar.exception;


public class DuplicateEntryException extends CustomException {
    public DuplicateEntryException(String erroMsg) {
        super(ErrorCodes.ERROR_DUPLICATE_ENTRY, erroMsg);
    }
}
