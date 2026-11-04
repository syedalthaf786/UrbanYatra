package com.ubranyatra.ui;

import com.ubranyatra.model.TourPackage;
import com.ubranyatra.model.UserRecord;
import com.ubranyatra.service.PackageService;
import com.ubranyatra.service.PricingService;
import com.ubranyatra.service.RideService;
import com.ubranyatra.service.UserService;
import com.ubranyatra.util.Console;
import java.io.IOException;

public class CustomerMenu {
    private final String username;
    public CustomerMenu(String username){this.username=username;}
    public void show() throws IOException {
        boolean running=true;
        while(running){
            System.out.println("\n========== CUSTOMER MENU ==========\n1. View Profile\n2. Update Profile\n3. View Tour Packages\n4. Book a Ride\n5. My Bookings\n6. Logout");
            switch(Console.read("Enter your choice: ")){
                case "1": profile();break;
                case "2": updateProfile();break;
                case "3": PackageService.display();break;
                case "4": bookRide();break;
                case "5": RideService.showForCustomer(username);break;
                case "6": running=false;System.out.println("Logged out.");break;
                default:System.out.println("Invalid choice.");
            }
        }
    }
    private void profile() throws IOException {UserRecord u=UserService.find(username);if(u==null)return;System.out.println("\nUsername: "+u.getUsername()+"\nName: "+u.getFullName()+"\nEmail: "+u.getEmail()+"\nPhone: "+u.getPhone());}
    private void updateProfile() throws IOException {String n=Console.read("New full name: "),e=Console.read("New email: "),p=Console.read("New phone: ");if(!valid(n)||!valid(e)||!valid(p)){System.out.println("All fields required; do not use |.");return;}System.out.println(UserService.update(username,n,e,p)?"Profile updated.":"Profile update failed.");}
    private void bookRide() throws IOException {
        TourPackage[] packages=PackageService.all();
        if(packages.length==0){System.out.println("No packages are available yet. Please try later.");return;}
        PackageService.display(); int id=Console.readInt("Select package ID: "); TourPackage selected=null;
        for(TourPackage p:packages)if(p.id==id){selected=p;break;}
        if(selected==null){System.out.println("Invalid package ID.");return;}
        System.out.println("\nSelected package: "+selected.name+"\nDestination: "+selected.destination+"\nEstimated trip distance: "+selected.distanceKm+" km");
        System.out.println("\n========== SELECT VEHICLE ==========\n1. Bike\n2. Auto\n3. Sedan\n4. SUV");
        String vehicleChoice=Console.read("Select vehicle: "); String vehicle;
        switch(vehicleChoice){case "1":vehicle="Bike";break;case "2":vehicle="Auto";break;case "3":vehicle="Sedan";break;case "4":vehicle="SUV";break;default:System.out.println("Invalid vehicle selection.");return;}
        String pickup=Console.read("Enter pickup location: ");
        if(!valid(pickup)){System.out.println("Pickup location is required.");return;}
        double fare=PricingService.calculateFare(vehicle,selected.distanceKm);
        UserRecord customer=UserService.find(username);
        System.out.println("\n========== UBRANYATRA BILL ==========");
        System.out.println("Customer          : "+(customer==null?username:customer.getFullName()));
        System.out.println("Package           : "+selected.name);
        System.out.println("Vehicle           : "+vehicle);
        System.out.println("Pickup Location   : "+pickup);
        System.out.println("Destination       : "+selected.destination);
        System.out.printf("Estimated Distance: %.1f km%n",selected.distanceKm);
        System.out.println("-------------------------------------");
        System.out.printf("TOTAL FARE         : Rs. %.2f%n",fare);
        System.out.println("=====================================");
        String confirm=Console.read("Confirm booking? (Y/N): ");
        if(confirm.equalsIgnoreCase("Y")){RideService.create(username,selected.name,vehicle,pickup,selected.destination,selected.distanceKm,fare);System.out.println("Booking successful!\nStatus: PENDING");System.out.printf("Fare: Rs. %.2f%n",fare);}
        else System.out.println("Booking cancelled.");
    }
    private boolean valid(String s){return Console.validField(s);}
}
