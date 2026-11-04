package com.ubranyatra.model;

/** Rider account; inherits shared user data and overrides role behavior. */
public class RiderRecord extends UserRecord {
    public RiderRecord(String username, String password, String fullName, String email, String phone) {
        super(username, password, "RIDER", fullName, email, phone);
    }
    @Override public String getWelcomeMessage() { return "Welcome, " + getFullName() + "! Check your available rides."; }
}
