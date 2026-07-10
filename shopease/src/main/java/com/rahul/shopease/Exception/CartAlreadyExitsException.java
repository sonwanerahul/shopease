package com.rahul.shopease.Exception;

public class CartAlreadyExitsException extends RuntimeException {
    public CartAlreadyExitsException(String message) {
        super(message);
    }
}
