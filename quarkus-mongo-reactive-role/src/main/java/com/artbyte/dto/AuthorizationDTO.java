package com.artbyte.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AuthorizationDTO {
    
    @NotBlank
    private String user;
    
    @NotEmpty
    private String[] roles;


    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String[] getRoles() {
        return roles;
    }
    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
