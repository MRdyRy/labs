package com.rudy.ryanto.socialmedia.user.management.exception;

public class UserManagementException extends RuntimeException{

    public UserManagementException(String m){
        super(m);
    }

    public UserManagementException(String m, Throwable t){
        super(m,t);
    }
}
