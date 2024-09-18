package com.zbyszkobud.model;

import javax.persistence.Embeddable;

@Embeddable
public class ContactData {
    private String phoneNumber;
    private String email;

    public ContactData() {}

    public ContactData(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Gettery i settery
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}