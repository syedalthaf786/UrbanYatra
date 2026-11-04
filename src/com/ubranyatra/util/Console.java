package com.ubranyatra.util;

import java.util.Scanner;

public final class Console {
    public static final Scanner SC = new Scanner(System.in);
    private Console() {}
    public static String read(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }
    public static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(read(prompt)); }
            catch (NumberFormatException ex) { System.out.println("Please enter a valid whole number."); }
        }
    }
    public static double readDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(read(prompt)); }
            catch (NumberFormatException ex) { System.out.println("Please enter a valid number."); }
        }
    }
    public static boolean validField(String value) {
        return value != null && !value.trim().isEmpty() && !value.contains("|");
    }
}
