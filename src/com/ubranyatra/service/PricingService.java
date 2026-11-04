package com.ubranyatra.service;

public class PricingService {
    public static double calculateFare(String vehicle,double distanceKm) {
        if(distanceKm<=0) throw new IllegalArgumentException("Distance must be greater than zero.");
        double base,perKm;
        switch(vehicle.toLowerCase()) {
            case "bike": base=15; perKm=6; break;
            case "auto": base=25; perKm=10; break;
            case "sedan": base=40; perKm=14; break;
            case "suv": base=60; perKm=18; break;
            default: throw new IllegalArgumentException("Invalid vehicle type.");
        }
        return Math.round((base+distanceKm*perKm)*100.0)/100.0;
    }
}
