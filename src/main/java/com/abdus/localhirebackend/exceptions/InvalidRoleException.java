package com.abdus.localhirebackend.exceptions;

public class InvalidRoleException extends RuntimeException{
    public InvalidRoleException(String msg){
        super(msg);
    }
}
