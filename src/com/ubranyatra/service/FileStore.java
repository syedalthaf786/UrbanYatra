package com.ubranyatra.service;

import java.io.*;

final class FileStore {
    private FileStore() {}
    static File file(String name) throws IOException {
        File dir = new File("data");
        if (!dir.exists() && !dir.mkdirs()) throw new IOException("Could not create data directory.");
        return new File(dir, name);
    }
    static String[] readLines(String name) throws IOException {
        File f=file(name);
        if(!f.exists()) return new String[0];
        String[] lines=new String[0];
        try(BufferedReader br=new BufferedReader(new FileReader(f))) {
            String line;
            while((line=br.readLine())!=null) {
                String[] next=new String[lines.length+1];
                System.arraycopy(lines,0,next,0,lines.length); next[lines.length]=line; lines=next;
            }
        }
        return lines;
    }
    static void writeLines(String name, String[] lines) throws IOException {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file(name)))) {
            for(String line:lines) { bw.write(line); bw.newLine(); }
        }
    }
    static void appendLine(String name, String line) throws IOException {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file(name),true))) { bw.write(line); bw.newLine(); }
    }
}
