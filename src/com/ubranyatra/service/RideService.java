package com.ubranyatra.service;

import com.ubranyatra.model.RideRecord;
import java.io.IOException;

public class RideService {
    public static RideRecord[] all() throws IOException {
        String[] lines=FileStore.readLines("rides.txt"); RideRecord[] out=new RideRecord[0];
        for(String line:lines) {RideRecord r=RideRecord.fromLine(line);if(r!=null)out=append(out,r);} return out;
    }
    private static RideRecord[] append(RideRecord[] a,RideRecord r){RideRecord[] b=new RideRecord[a.length+1];System.arraycopy(a,0,b,0,a.length);b[a.length]=r;return b;}
    public static int nextId() throws IOException {int max=0;for(RideRecord r:all())if(r.id>max)max=r.id;return max+1;}
    public static void create(String customer,String packageName,String vehicle,String pickup,String destination,double distance,double fare)throws IOException{
        RideRecord r=new RideRecord(nextId(),customer,packageName,vehicle,pickup,destination,distance,fare,"PENDING","");FileStore.appendLine("rides.txt",r.toLine());System.out.println("Ride ID: "+r.id);
    }
    public static void showForCustomer(String customer)throws IOException{boolean found=false;for(RideRecord r:all())if(r.customer.equalsIgnoreCase(customer)){found=true;show(r);}if(!found)System.out.println("No bookings found.");}
    public static void showPending()throws IOException{boolean found=false;System.out.println("\n========== AVAILABLE RIDES ==========");for(RideRecord r:all())if(r.status.equals("PENDING")){found=true;show(r);}if(!found)System.out.println("No pending rides.");}
    public static void showForRider(String rider)throws IOException{boolean found=false;for(RideRecord r:all())if(r.rider.equalsIgnoreCase(rider)){found=true;show(r);}if(!found)System.out.println("No rides assigned to you.");}
    public static void showAll()throws IOException{for(RideRecord r:all())show(r);}
    private static void show(RideRecord r){System.out.printf("Ride ID: %d%nCustomer: %s%nPackage: %s%nVehicle: %s%nPickup: %s%nDestination: %s%nDistance: %.1f km%nFare: Rs. %.2f%nStatus: %s%nRider: %s%n----------------------------%n",r.id,r.customer,r.packageName,r.vehicle,r.pickup,r.destination,r.distanceKm,r.fare,r.status,r.rider);}
    public static boolean riderAction(int id,String rider,String action)throws IOException{RideRecord[] rs=all();boolean found=false;for(RideRecord r:rs)if(r.id==id&&r.status.equals("PENDING")){if(action.equals("ACCEPT")){r.status="ACCEPTED";r.rider=rider;}else if(action.equals("REJECT")){r.status="REJECTED";r.rider=rider;}else return false;found=true;break;}if(found)write(rs);return found;}
    public static boolean complete(int id,String rider)throws IOException{RideRecord[] rs=all();boolean found=false;for(RideRecord r:rs)if(r.id==id&&r.rider.equalsIgnoreCase(rider)&&r.status.equals("ACCEPTED")){r.status="COMPLETED";found=true;break;}if(found)write(rs);return found;}
    public static void riderStats(String rider)throws IOException{int count=0;double earnings=0;for(RideRecord r:all())if(r.rider.equalsIgnoreCase(rider)&&r.status.equals("COMPLETED")){count++;earnings+=r.fare;}System.out.println("Completed rides: "+count);System.out.printf("Total earnings: Rs. %.2f%n",earnings);}
    public static void summary()throws IOException{RideRecord[] rs=all();int pending=0,accepted=0,completed=0,rejected=0;double revenue=0;for(RideRecord r:rs){if(r.status.equals("PENDING"))pending++;else if(r.status.equals("ACCEPTED"))accepted++;else if(r.status.equals("COMPLETED")){completed++;revenue+=r.fare;}else if(r.status.equals("REJECTED"))rejected++;}System.out.println("\n========== RIDE STATISTICS ==========");System.out.println("Total rides: "+rs.length+"\nPending: "+pending+"\nAccepted: "+accepted+"\nCompleted: "+completed+"\nRejected: "+rejected);System.out.printf("Completed ride revenue: Rs. %.2f%n",revenue);}
    private static void write(RideRecord[] rs)throws IOException{String[] lines=new String[rs.length];for(int i=0;i<rs.length;i++)lines[i]=rs[i].toLine();FileStore.writeLines("rides.txt",lines);}
}
