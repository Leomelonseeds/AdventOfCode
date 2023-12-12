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
    
    public List<String> toList(String... strs) {
        List<String> r = new ArrayList<>();
        for (String s : strs) {
            r.add(s);
        }
        return r;
    }
    
    public List<Integer> toList(int... is) {
        List<Integer> r = new ArrayList<>();
        for (int i : is) {
            r.add(i);
        }
        return r;
    }
    

    
    // THANKS GEEKSFORGEEKS LMAO
    // THIS SECTION IS FOR findNextPermutation, which
    // Function to swap the data
    // present in the left and right indices
    public static int[] swap(int data[], int left, int right)
    {
 
        // Swap the data
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
 
        // Return the updated array
        return data;
    }
 
    // Function to reverse the sub-array
    // starting from left to the right
    // both inclusive
    public static int[] reverse(int data[], int left, int right)
    {
 
        // Reverse the sub-array
        while (left < right) {
            int temp = data[left];
            data[left++] = data[right];
            data[right--] = temp;
        }
 
        // Return the updated array
        return data;
    }
 
    // Function to find the next permutation
    // of the given integer array
    public static boolean findNextPermutation(int data[])
    {
 
        // If the given dataset is empty
        // or contains only one element
        // next_permutation is not possible
        if (data.length <= 1)
            return false;
 
        int last = data.length - 2;
 
        // find the longest non-increasing suffix
        // and find the pivot
        while (last >= 0) {
            if (data[last] < data[last + 1]) {
                break;
            }
            last--;
        }
 
        // If there is no increasing pair
        // there is no higher order permutation
        if (last < 0)
            return false;
 
        int nextGreater = data.length - 1;
 
        // Find the rightmost successor to the pivot
        for (int i = data.length - 1; i > last; i--) {
            if (data[i] > data[last]) {
                nextGreater = i;
                break;
            }
        }
 
        // Swap the successor and the pivot
        data = swap(data, nextGreater, last);
 
        // Reverse the suffix
        data = reverse(data, last + 1, data.length - 1);
 
        // Return true as the next_permutation is done
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
