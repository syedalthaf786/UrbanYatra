package com.ubranyatra.service;

import com.ubranyatra.model.UserRecord;
import java.io.IOException;

public class UserService {
    public static UserRecord[] all() throws IOException {
        String[] lines=FileStore.readLines("users.txt"); UserRecord[] out=new UserRecord[0];
        for(String line:lines) { UserRecord u=UserRecord.fromLine(line); if(u!=null) out=append(out,u); }
        return out;
    }
    private static UserRecord[] append(UserRecord[] a, UserRecord u) { UserRecord[] b=new UserRecord[a.length+1]; System.arraycopy(a,0,b,0,a.length); b[a.length]=u; return b; }
    public static UserRecord find(String username) throws IOException {
        for(UserRecord u:all()) if(u.getUsername().equalsIgnoreCase(username)) return u; return null;
    }
    public static UserRecord authenticate(String username,String password) throws IOException {
        UserRecord u=find(username); return u!=null && u.getPassword().equals(password)?u:null;
    }
    public static boolean add(UserRecord u) throws IOException {
        if(find(u.getUsername())!=null) return false; FileStore.appendLine("users.txt",u.toLine()); return true;
    }
    public static boolean update(String username,String name,String email,String phone) throws IOException {
        UserRecord[] users=all(); boolean found=false;
        for(UserRecord u:users) if(u.getUsername().equalsIgnoreCase(username)) {u.setFullName(name);u.setEmail(email);u.setPhone(phone);found=true;}
        if(found) write(users); return found;
    }
    public static boolean delete(String username) throws IOException {
        UserRecord[] users=all(); UserRecord[] kept=new UserRecord[0]; boolean found=false;
        for(UserRecord u:users) {
            if(u.getUsername().equalsIgnoreCase(username) && !u.getRole().equals("ADMIN")) found=true;
            else kept=append(kept,u);
        }
        if(found) write(kept); return found;
    }
    public static void displayRole(String role) throws IOException {
        boolean found=false; System.out.println("\n========== "+role+" ACCOUNTS ==========");
        for(UserRecord u:all()) if(u.getRole().equals(role)) { found=true; System.out.println("Username: "+u.getUsername()+"\nName: "+u.getFullName()+"\nEmail: "+u.getEmail()+"\nPhone: "+u.getPhone()+"\n----------------------------"); }
        if(!found) System.out.println("No accounts found.");
    }
    private static void write(UserRecord[] users) throws IOException { String[] lines=new String[users.length]; for(int i=0;i<users.length;i++) lines[i]=users[i].toLine(); FileStore.writeLines("users.txt",lines); }
}
