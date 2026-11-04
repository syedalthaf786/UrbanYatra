
package com.ubranyatra.service;
import com.ubranyatra.model.TourPackage;
import java.io.IOException;

public class PackageService {

    public static TourPackage[] all() throws IOException {
        String[] lines = FileStore.readLines("packages.txt");
        TourPackage[] out = new TourPackage[0];

        for (String line : lines) {
            TourPackage p = TourPackage.fromLine(line);
            if (p != null) {
                out = append(out, p);
            }
        }

        return out;
    }

    private static TourPackage[] append(TourPackage[] a, TourPackage p) {
        TourPackage[] b = new TourPackage[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length] = p;
        return b;
    }

    public static int nextId() throws IOException {
        int max = 0;

        for (TourPackage p : all()) {
            if (p.id > max) {
                max = p.id;
            }
        }

        return max + 1;
    }

    // Create a package with tourist places
    public static void create(String name, String description,
                              String destination, double distanceKm,
                              String[] places) throws IOException {

        TourPackage p = new TourPackage(
                nextId(), name, description, destination, distanceKm, places
        );

        FileStore.appendLine("packages.txt", p.toLine());
    }

    // Keep the old create method for compatibility
    public static void create(String name, String description,
                              String destination, double distanceKm)
            throws IOException {

        create(name, description, destination, distanceKm, new String[0]);
    }

    // Update a package, including its tourist places
    public static boolean update(int id, String name, String description,
                                 String destination, double distanceKm,
                                 String[] places) throws IOException {

        TourPackage[] ps = all();
        boolean found = false;

        for (TourPackage p : ps) {
            if (p.id == id) {
                p.name = name;
                p.description = description;
                p.destination = destination;
                p.distanceKm = distanceKm;
                p.places = (places == null) ? new String[0] : places.clone();
                found = true;
                break;
            }
        }

        if (found) {
            write(ps);
        }

        return found;
    }

    // Keep the old update method for compatibility
    public static boolean update(int id, String name, String description,
                                 String destination, double distanceKm)
            throws IOException {

        TourPackage[] ps = all();

        for (TourPackage p : ps) {
            if (p.id == id) {
                return update(id, name, description, destination,
                              distanceKm, p.places);
            }
        }

        return false;
    }

    public static boolean delete(int id) throws IOException {
        TourPackage[] ps = all();
        TourPackage[] kept = new TourPackage[0];
        boolean found = false;

        for (TourPackage p : ps) {
            if (p.id == id) {
                found = true;
            } else {
                kept = append(kept, p);
            }
        }

        if (found) {
            write(kept);
        }

        return found;
    }

    private static void write(TourPackage[] ps) throws IOException {
        String[] lines = new String[ps.length];

        for (int i = 0; i < ps.length; i++) {
            lines[i] = ps[i].toLine();
        }

        FileStore.writeLines("packages.txt", lines);
    }

    // Display packages and all tourist places
    public static void display() throws IOException {
        TourPackage[] ps = all();

        System.out.println("\n========== TOUR PACKAGES ==========");

        if (ps.length == 0) {
            System.out.println("No packages available. Ask admin to create packages.");
            return;
        }

        for (TourPackage p : ps) {
            System.out.println("\nPackage ID: " + p.id);
            System.out.println("Package Name: " + p.name);
            System.out.println("Description: " + p.description);
            System.out.println("Destination: " + p.destination);
            System.out.printf("Estimated Distance: %.1f km%n", p.distanceKm);

            System.out.println("Places to Visit:");

            if (p.places == null || p.places.length == 0) {
                System.out.println("    Places not added yet.");
            } else {
                for (int i = 0; i < p.places.length; i++) {
                    System.out.println("    " + (i + 1) + ". " + p.places[i]);
                }
            }

            System.out.println("-----------------------------------");
        }
    }
}
