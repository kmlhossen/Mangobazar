package com.mangobazar.exception;


public abstract class CustomException extends  Exception {
    private int errorCode;
    private String errorMsg;

    public CustomException(int code, String msg){
        super(msg);
        errorMsg = msg;
        errorCode = code;
    }

    public CustomException(int code, String msg, Throwable cause){
        super(msg, cause);
        errorMsg = msg;
        errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
