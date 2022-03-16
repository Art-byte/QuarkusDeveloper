package com.artbyte.model;

import java.util.Collections;

import java.util.Set;


public class User {
    
    public String username;
    public String password;
    public Set<Role> roles;

    public User(){}

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }


    public static User findByUsername(String username){

        String userUserName = "user";
        String userPassword = "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=";
        String adminUserName = "admin";
        String adminPassword = "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=";

        if(username.equals(userUserName)){
            return new User(userUserName, userPassword, Collections.singleton(Role.USER));
        }else if(username.equals(adminUserName)){
            return new User(adminUserName, adminPassword, Collections.singleton(Role.ADMIN));
        }else{
            return null;
        }
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User [password=" + password + ", roles=" + roles + ", username=" + username + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (roles == null) {
            if (other.roles != null)
                return false;
        } else if (!roles.equals(other.roles))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    
}
