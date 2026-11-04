package com.ubranyatra.model;

/**
 * Abstract parent for all Ubranyatra account types.
 * Demonstrates abstraction and encapsulation.
 */
public abstract class UserRecord {
    private String username;
    private String password;
    private final String role;
    private String fullName;
    private String email;
    private String phone;

    protected UserRecord(String username, String password, String role,
                         String fullName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    /** Subclasses provide role-specific behavior: runtime polymorphism. */
    public abstract String getWelcomeMessage();

    public String toLine() {
        return username + "|" + password + "|" + role + "|" + fullName + "|" + email + "|" + phone;
    }

    public static UserRecord create(String username, String password, String role,
                                    String fullName, String email, String phone) {
        if (role == null) return null;
        switch (role.toUpperCase()) {
            case "CUSTOMER": return new CustomerRecord(username, password, fullName, email, phone);
            case "RIDER": return new RiderRecord(username, password, fullName, email, phone);
            case "ADMIN": return new AdminRecord(username, password, fullName, email, phone);
            default: return null;
        }
    }

    public static UserRecord fromLine(String line) {
        if (line == null) return null;
        String[] p = line.split("\\|", -1);
        if (p.length != 6) return null;
        return create(p[0], p[1], p[2], p[3], p[4], p[5]);
    }
}
