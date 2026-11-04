package com.ubranyatra.model;

/** Customer account; inherits shared user data and overrides role behavior. */
public class CustomerRecord extends UserRecord {
    public CustomerRecord(String username, String password, String fullName, String email, String phone) {
        super(username, password, "CUSTOMER", fullName, email, phone);
    }
    @Override public String getWelcomeMessage() { return "Welcome, " + getFullName() + "! Ready to book your next journey?"; }
}
