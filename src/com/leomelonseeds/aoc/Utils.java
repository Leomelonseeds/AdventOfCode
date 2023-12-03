package com.leomelonseeds.aoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    
    public static List<String> getInput() {
        return getInput("input");
    }
    
    // Load file
    public static List<String> getInput(String type) {
        ArrayList<String> text = new ArrayList<>();
        try {
            File input = new File(type + ".txt");
            Scanner scanner;
            scanner = new Scanner(input);
            while(scanner.hasNextLine()) {
                text.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {}
        return text;
    }
    
    public static boolean isDigit(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    //Extra helper functions
    public static void print(Object s) {
        System.out.print(s);
    }
    
    public static void println(Object s) {
        System.out.println(s);
    }
    
    public static void println() {
        System.out.println();
    }
}
