package com.ubranyatra.ui;

import com.ubranyatra.model.UserRecord;
import com.ubranyatra.service.RideService;
import com.ubranyatra.service.UserService;
import com.ubranyatra.util.Console;
import java.io.IOException;

public class RiderMenu {
    private final String username;
    public RiderMenu(String username){this.username=username;}
    public void show() throws IOException {
        boolean running=true;
        while(running){
            System.out.println("\n========== RIDER MENU ==========\n1. View Profile\n2. Update Profile\n3. Available Rides\n4. Accept/Reject Ride\n5. Complete Ride\n6. Earnings and Statistics\n7. My Rides\n8. Logout");
            switch(Console.read("Enter your choice: ")){
                case "1":profile();break;case "2":updateProfile();break;case "3":RideService.showPending();break;case "4":actionRide();break;
                case "5":completeRide();break;case "6":RideService.riderStats(username);break;case "7":RideService.showForRider(username);break;case "8":running=false;break;default:System.out.println("Invalid choice.");
            }
        }
    }
    private void profile()throws IOException{UserRecord u=UserService.find(username);if(u!=null)System.out.println("Username: "+u.getUsername()+"\nName: "+u.getFullName()+"\nEmail: "+u.getEmail()+"\nPhone: "+u.getPhone());}
    private void updateProfile()throws IOException{String n=Console.read("New full name: "),e=Console.read("New email: "),p=Console.read("New phone: ");if(!Console.validField(n)||!Console.validField(e)||!Console.validField(p)){System.out.println("All fields required; do not use |.");return;}System.out.println(UserService.update(username,n,e,p)?"Profile updated.":"Update failed.");}
    private void actionRide()throws IOException{RideService.showPending();int id=Console.readInt("Ride ID: ");String action=Console.read("Enter ACCEPT or REJECT: ").toUpperCase();System.out.println(RideService.riderAction(id,username,action)?"Ride updated.":"Could not update ride.");}
    private void completeRide()throws IOException{int id=Console.readInt("Ride ID: ");System.out.println(RideService.complete(id,username)?"Ride completed.":"Could not complete ride. Ensure you accepted it first.");}
}
