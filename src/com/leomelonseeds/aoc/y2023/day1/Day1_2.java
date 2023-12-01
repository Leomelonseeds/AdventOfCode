package com.leomelonseeds.aoc.y2023.day1;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day1_2 {

    public static void main(String[] args) {
        
        List<String> digits = new ArrayList<>(List.of(new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"}));
        
        int sum = 0;
        
        for (String s : Utils.getInput()) {
            
            int first = -1;
            int second = -1;
            String a = "";
            String b = "";
            
            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);
                if (isDigit(c)) {
                    first = Integer.parseInt(String.valueOf(c));
                    break;
                }
                
                boolean success = false;
                String sub = s.substring(0, i+1);
                for (String d1 : digits) {
                    if (sub.contains(d1)) {
                        a = d1;
                        success = true;
                        break;
                    }
                }
                if (success) {
                    break;
                }
            }
            
            for (int i = s.length() - 1; i >= 0; i--) {

                char c = s.charAt(i);
                if (isDigit(c)) {
                    second = Integer.parseInt(String.valueOf(c));
                    break;
                }
                
                boolean success = false;
                String sub = s.substring(i, s.length());
                for (String d1 : digits) {
                    if (sub.contains(d1)) {
                        b = d1;
                        success = true;
                        break;
                    }
                }
                if (success) {
                    break;
                }
            }
            
            first = first == -1 ? digits.indexOf(a) + 1 : first; 
            second = second == -1 ? digits.indexOf(b) + 1 : second; 
            sum += first * 10 + second;
        }
        
        Utils.print(sum);
    }
    
    public static boolean isDigit(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }

}
