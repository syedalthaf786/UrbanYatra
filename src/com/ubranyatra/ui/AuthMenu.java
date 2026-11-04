package com.ubranyatra.ui;

import com.ubranyatra.model.UserRecord;
import com.ubranyatra.service.UserService;
import com.ubranyatra.util.Console;
import java.io.IOException;

public class AuthMenu {
    private static final String ADMIN_USER="admin", ADMIN_PASS="admin123";
    public void show() {
        System.out.println("========================================\n             UBRANYATRA\n       Your Ride, Your Journey\n========================================");
        boolean running=true;
        while(running) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            String choice=Console.read("Enter your choice: ");
            try {
                switch(choice) {
                    case "1": register(); break;
                    case "2": login(); break;
                    case "3": running=false; System.out.println("Thank you for using Ubranyatra!"); break;
                    default: System.out.println("Invalid choice.");
                }
            } catch(IOException ex) {System.out.println("Could not access data files: "+ex.getMessage());}
        }
    }
    private void register() throws IOException {
        System.out.println("\n========== REGISTRATION ==========");
        String roleChoice=Console.read("1. Customer\n2. Rider\nChoose role: ");
        String role=roleChoice.equals("1")?"CUSTOMER":roleChoice.equals("2")?"RIDER":"";
        if(role.isEmpty()){System.out.println("Invalid role.");return;}
        String username=Console.read("Choose username: ");
        String password=Console.read("Choose password: ");
        String name=Console.read("Full name: "); String email=Console.read("Email: "); String phone=Console.read("Phone: ");
        if(username.equalsIgnoreCase(ADMIN_USER)||!Console.validField(username)||!Console.validField(password)||!Console.validField(name)||!Console.validField(email)||!Console.validField(phone)){System.out.println("All fields are required; do not use | character.");return;}
        if(!UserService.add(UserRecord.create(username,password,role,name,email,phone))){System.out.println("Username already exists.");return;}
        System.out.println("Registration successful!");
    }
    private void login() throws IOException {
        System.out.println("\n========== LOGIN ==========");
        String username=Console.read("Username: "); String password=Console.read("Password: ");
        if(username.equals(ADMIN_USER)&&password.equals(ADMIN_PASS)){new AdminMenu().show();return;}
        UserRecord user=UserService.authenticate(username,password);
        if(user==null){System.out.println("Invalid username or password.");return;}
        System.out.println(user.getWelcomeMessage());
        if(user.getRole().equals("CUSTOMER"))new CustomerMenu(user.getUsername()).show();
        else if(user.getRole().equals("RIDER"))new RiderMenu(user.getUsername()).show();
    }
}
