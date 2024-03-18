package com.olim.authservice.exception.customexception;

public class PermissionFailException extends RuntimeException{
    public PermissionFailException(String message) {
        super(message);
    }
}
