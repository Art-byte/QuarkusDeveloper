package com.artbyte.model;

public class AuthRequest {

    public String username;
    public String password;

    public AuthRequest() {
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return "AuthRequest [password=" + password + ", username=" + username + "]";
    }


    
    
    
}
