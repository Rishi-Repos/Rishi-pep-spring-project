package com.example.exception;

public class UnsuccessfulMessagePostException extends Exception {
    public UnsuccessfulMessagePostException(String message){
        super(message);
    }
}
