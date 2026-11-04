package com.ubranyatra.model;

/** Admin account type, included in the same role hierarchy. */
public class AdminRecord extends UserRecord {
    public AdminRecord(String username, String password, String fullName, String email, String phone) {
        super(username, password, "ADMIN", fullName, email, phone);
    }
    @Override public String getWelcomeMessage() { return "Welcome, " + getFullName() + "! You have administrator access."; }
}
