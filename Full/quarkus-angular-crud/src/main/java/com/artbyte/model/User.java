package com.artbyte.model;

import org.bson.types.ObjectId;

public class User {

    public ObjectId id;
    private String firstName;
    private String lastName;
    private String emailId;
    
    public User(){
        super();
    }
    
    public User(ObjectId id, String firstName, String lastName, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastnName) {
        this.lastName = lastnName;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    

    
}
