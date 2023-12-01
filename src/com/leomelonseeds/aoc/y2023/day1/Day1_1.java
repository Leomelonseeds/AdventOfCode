package com.leomelonseeds.aoc.y2023.day1;

import com.leomelonseeds.aoc.Utils;

public class Day1_1 {

    public static void main(String[] args) {
        int sum = 0;
        for (String s : Utils.getInput()) {
            int a = 0;
            int b = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDigit(c)) {
                    a = Integer.parseInt(String.valueOf(c));
                    break;
                }
            }
            
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);

                if (isDigit(c)) {
                    b = Integer.parseInt(String.valueOf(c));
                    break;
                }
            }
            
            sum += a * 10 + b;
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
