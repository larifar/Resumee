package com.larissa.services;

public class ResumeeException extends Exception{
    public ResumeeException(String msg){
        super(msg);
    }

    public ResumeeException(String msg, Throwable cause){
        super(msg, cause);
    }
}
