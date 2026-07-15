package com.rahul.shopease.Exception;

public class OrderAlreadyDeliverdException extends RuntimeException {
    public OrderAlreadyDeliverdException(String message) {
        super(message);
    }
}
