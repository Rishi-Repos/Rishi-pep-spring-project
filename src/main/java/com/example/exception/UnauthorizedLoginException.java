package com.example.exception;

public class UnauthorizedLoginException extends Exception{
    public UnauthorizedLoginException(String message){
        super(message);
    }
}
