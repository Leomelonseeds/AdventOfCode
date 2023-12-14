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
            Long.parseLong(String.valueOf(c));
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean isDigit(String s) {
        try {
            Long.parseLong(String.valueOf(s));
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    public static List<Integer> strToList(String s){
        String[] args = s.split(" ");
        List<Integer> r = new ArrayList<>();
        for (String a : args) {
            r.add(Integer.parseInt(a));
        }
        return r;
    }
    
    public static String trimStr(String s) {
        return s.trim().replaceAll(" +", " ");
    }
    
    public static List<String> toList(String... strs) {
        List<String> r = new ArrayList<>();
        for (String s : strs) {
            r.add(s);
        }
        return r;
    }
    
    public static List<Integer> toList(int... is) {
        List<Integer> r = new ArrayList<>();
        for (int i : is) {
            r.add(i);
        }
        return r;
    }
    
    public static <T> int toBinary(List<T> l, T on) {
        int r = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).equals(on)) {
                r = r | (int) Math.pow(2, (l.size() - i - 1));
            }
        }
        
        return r;
    }
    
    public static <T> int toBinary(T[] l, T on) {
        int r = 0;
        for (int i = 0; i < l.length; i++) {
            if (l[i].equals(on)) {
                r = r | (int) Math.pow(2, (l.length - i - 1));
            }
        }
        
        return r;
    }
    
    public static <T> void printGrid(List<List<T>> l) {
        for (List<T> lt : l) {
            Utils.println(lt);
        }
    }
    
    /**
     * Return a column-by-column list from the given list
     * 
     * @param <T>
     * @param l Each list must have equal length
     * @return
     */
    public static <T> List<List<T>> verticalList(List<List<T>> l){
        List<List<T>> r = new ArrayList<>();
        for (int j = 0; j < l.get(0).size(); j++) {
            List<T> cr = new ArrayList<>();
            for (int k = 0; k < l.size(); k++) {
                cr.add(l.get(k).get(j));
            }
            
            r.add(cr);
        }
        
        return r;
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
