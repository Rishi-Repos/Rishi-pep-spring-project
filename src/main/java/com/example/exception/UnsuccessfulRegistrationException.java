package com.example.exception;

public class UnsuccessfulRegistrationException extends Exception{
    public UnsuccessfulRegistrationException(String message){
        super(message);
    }
}
