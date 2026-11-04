package com.ubranyatra.model;

public class RideRecord {
    public int id;
    public String customer, packageName, vehicle, pickup, destination, status, rider;
    public double distanceKm, fare;
    public RideRecord(int id, String customer, String packageName, String vehicle, String pickup,
                      String destination, double distanceKm, double fare, String status, String rider) {
        this.id=id; this.customer=customer; this.packageName=packageName; this.vehicle=vehicle; this.pickup=pickup;
        this.destination=destination; this.distanceKm=distanceKm; this.fare=fare; this.status=status; this.rider=rider;
    }
    public String toLine() {
        return id+"|"+customer+"|"+packageName+"|"+vehicle+"|"+pickup+"|"+destination+"|"+distanceKm+"|"+fare+"|"+status+"|"+rider;
    }
    public static RideRecord fromLine(String line) {
        String[] p=line.split("\\|",-1);
        if(p.length!=10) return null;
        try { return new RideRecord(Integer.parseInt(p[0]),p[1],p[2],p[3],p[4],p[5],Double.parseDouble(p[6]),Double.parseDouble(p[7]),p[8],p[9]); }
        catch(NumberFormatException ex) { return null; }
    }
}
