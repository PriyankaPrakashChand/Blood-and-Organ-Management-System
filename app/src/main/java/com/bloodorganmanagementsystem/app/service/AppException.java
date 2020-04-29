package com.bloodorganmanagementsystem.app.service;

import lombok.Getter;

@Getter
public class AppException extends Exception {
 
    public AppException(String message) {
        super(message);
    }

}