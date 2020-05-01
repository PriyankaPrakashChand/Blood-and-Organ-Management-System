package com.bloodorganmanagementsystem.app.service.exception;

import lombok.Getter;

@Getter
public class AppException extends Exception {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AppException(String message) {
        super(message);
    }

}