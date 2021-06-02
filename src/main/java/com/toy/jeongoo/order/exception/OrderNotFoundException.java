package com.toy.jeongoo.order.exception;

public class OrderNotFoundException extends RuntimeException {

    private OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
