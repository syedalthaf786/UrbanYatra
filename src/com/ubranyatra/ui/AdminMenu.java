
package com.ubranyatra.ui;
import com.ubranyatra.service.PackageService;
import com.ubranyatra.service.RideService;
import com.ubranyatra.service.UserService;
import com.ubranyatra.util.Console;
import java.io.IOException;

public class AdminMenu {

    public void show() throws IOException {
        boolean running = true;

        while (running) {
            System.out.println(
                "\n========== ADMIN MENU ==========\n" +
                "1. Create Tour Package\n" +
                "2. View Tour Packages\n" +
                "3. Update Tour Package\n" +
                "4. Delete Tour Package\n" +
                "5. View Customers\n" +
                "6. View Riders\n" +
                "7. Update Account\n" +
                "8. Delete Account\n" +
                "9. View All Rides\n" +
                "10. Ride Statistics\n" +
                "11. Logout"
            );

            switch (Console.read("Enter your choice: ")) {
                case "1":
                    createPackage();
                    break;

                case "2":
                    PackageService.display();
                    break;

                case "3":
                    updatePackage();
                    break;

                case "4":
                    deletePackage();
                    break;

                case "5":
                    UserService.displayRole("CUSTOMER");
                    break;

                case "6":
                    UserService.displayRole("RIDER");
                    break;

                case "7":
                    updateAccount();
                    break;

                case "8":
                    deleteAccount();
                    break;

                case "9":
                    RideService.showAll();
                    break;

                case "10":
                    RideService.summary();
                    break;

                case "11":
                    running = false;
                    System.out.println("Admin logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Create a package with tourist places
    private void createPackage() throws IOException {
        System.out.println("\n========== CREATE TOUR PACKAGE ==========");

        String name = Console.read("Package name: ");
        String description = Console.read("Description: ");
        String destination = Console.read("Destination: ");
        double distance = Console.readDouble("Estimated trip distance (km): ");

        if (!valid(name) || !valid(description) ||
            !valid(destination) || distance <= 0) {
            System.out.println("Invalid details. Distance must be positive; do not use |.");
            return;
        }

        String[] places = readPlaces();

        PackageService.create(
            name, description, destination, distance, places
        );

        System.out.println("Tour package created successfully.");
    }

    // Update a package, including its itinerary
    private void updatePackage() throws IOException {
        PackageService.display();

        int id = Console.readInt("Package ID to update: ");

        String name = Console.read("New package name: ");
        String description = Console.read("New description: ");
        String destination = Console.read("New destination: ");
        double distance = Console.readDouble("New estimated distance (km): ");

        if (!valid(name) || !valid(description) ||
            !valid(destination) || distance <= 0) {
            System.out.println("Invalid details. Distance must be positive.");
            return;
        }

        String[] places = readPlaces();

        boolean updated = PackageService.update(
            id, name, description, destination, distance, places
        );

        System.out.println(
            updated ? "Package updated successfully."
                    : "Package not found."
        );
    }

    // Read tourist places into a String array
    private String[] readPlaces() {
        System.out.println("\n========== TOUR ITINERARY ==========");
        System.out.println("Enter tourist places one at a time.");
        System.out.println("Type DONE when you have finished.");

        String[] places = new String[0];

        while (true) {
            String place = Console.read(
                "Tourist place " + (places.length + 1) + ": "
            );

            if (place.equalsIgnoreCase("DONE")) {
                break;
            }

            if (!valid(place) || place.contains(";")) {
                System.out.println(
                    "Place cannot be empty or contain | or ;. Try again."
                );
                continue;
            }

            String[] updatedPlaces = new String[places.length + 1];

            System.arraycopy(
                places, 0, updatedPlaces, 0, places.length
            );

            updatedPlaces[places.length] = place;
            places = updatedPlaces;
        }

        if (places.length == 0) {
            System.out.println("No tourist places were added.");
        }

        return places;
    }

    // Delete a package
    private void deletePackage() throws IOException {
        PackageService.display();

        int id = Console.readInt("Package ID to delete: ");

        System.out.println(
            PackageService.delete(id)
                ? "Package deleted."
                : "Package not found."
        );
    }

    // Update a user account
    private void updateAccount() throws IOException {
        String username = Console.read("Username to update: ");

        String name = Console.read("New full name: ");
        String email = Console.read("New email: ");
        String phone = Console.read("New phone: ");

        if (!valid(name) || !valid(email) || !valid(phone)) {
            System.out.println("Invalid details.");
            return;
        }

        System.out.println(
            UserService.update(username, name, email, phone)
                ? "Account updated."
                : "Account not found."
        );
    }

    // Delete a user account
    private void deleteAccount() throws IOException {
        String username = Console.read("Username to delete: ");

        System.out.println(
            UserService.delete(username)
                ? "Account deleted."
                : "Account not found or cannot be deleted."
        );
    }

    private boolean valid(String value) {
        return Console.validField(value);
    }
}
