
package com.ubranyatra.model;

public class TourPackage {
    public int id;
    public String name, description, destination;
    public double distanceKm;
    public String[] places;

    // Constructor without tourist places
    public TourPackage(int id, String name, String description,
                       String destination, double distanceKm) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.places = new String[0];
    }

    // Constructor with tourist places
    public TourPackage(int id, String name, String description,
                       String destination, double distanceKm,
                       String[] places) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.places = (places == null) ? new String[0] : places.clone();
    }

    // Convert package information into a text-file line
    public String toLine() {
        String placesText = String.join(";", places);

        return id + "|" + name + "|" + description + "|"
                + destination + "|" + distanceKm + "|" + placesText;
    }

    // Create a TourPackage object from a text-file line
    public static TourPackage fromLine(String line) {
        String[] p = line.split("\\|", -1);

        // Support old records (5 fields) and new records (6 fields)
        if (p.length != 5 && p.length != 6) {
            return null;
        }

        try {
            int id = Integer.parseInt(p[0]);
            double distanceKm = Double.parseDouble(p[4]);

            String[] places = new String[0];

            if (p.length == 6 && !p[5].isEmpty()) {
                places = p[5].split(";", -1);
            }

            return new TourPackage(
                    id,
                    p[1],
                    p[2],
                    p[3],
                    distanceKm,
                    places
            );

        } catch (NumberFormatException ex) {
            return null;
        }
    }
}