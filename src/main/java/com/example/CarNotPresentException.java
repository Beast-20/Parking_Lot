package com.example;

public class CarNotPresentException extends Exception {
    public CarNotPresentException(String message){
        super(message);
    }
}
