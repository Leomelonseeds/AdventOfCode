package com.leomelonseeds.aoc.y2022.day13;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day13_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<String[]> pairs = new ArrayList<>();
        for (int i = 0; i < (input.size() + 1) / 3; i++) {
            String[] pair = {input.get(i * 3), input.get(i * 3 + 1)};
            pairs.add(pair);
        }
        
        int count = 0;
        for (int i = 0; i < pairs.size(); i++) {
            String[] pair = pairs.get(i);
            if (compare(pair[0], pair[1])) {
                Utils.println("Correct: " + (i + 1));
                count += i + 1;
            }
        }
        
        Utils.println(count);
    }
    
    // Returns true if items are in right order
    private static Boolean compare(String left, String right) {
        if (closing(left) && closing(right)) {
            return null;
        } else if (closing(left)) {
            return true;
        } else if (closing(right)) {
            return false;
        }
        Utils.println("Comparing " + left + " and " + right);
        boolean leftArray = left.charAt(0) == '[';
        boolean rightArray = right.charAt(0) == '[';
        if (leftArray && rightArray) {
            int leftClosing = findClosing(left);
            int rightClosing = findClosing(right);
            Boolean compared = compare(left.substring(1, leftClosing), right.substring(1, rightClosing));
            if (compared != null) {
                return compared;
            }
            left = left.substring(leftClosing);
            right = right.substring(rightClosing);
        } else if (leftArray && !rightArray) {
            return compare(left, withBrackets(right));
        } else if (!leftArray && rightArray) {
            return compare(withBrackets(left), right);
        }
        
        int l = Character.getNumericValue(left.charAt(0));
        int r = Character.getNumericValue(right.charAt(0));
        if (l == 1 && left.length() > 1 && left.charAt(1) == '0') {
            l = 10;
        }
        if (r == 1 && right.length() > 1 && right.charAt(1) == '0') {
            r = 10;
        }
        if (l != -1) {
            Utils.println("Comparing integers " + l + " and " + r);
        }
        if (l < r) {
            return true;
        } 
        
        if (l > r) {
            return false;
        }
        return compare(left.substring(l == 10 ? 2 : 1), right.substring(l == 10 ? 2 : 1));
    }
    
    private static String withBrackets(String s) {
        if (s.length() > 1 && s.substring(0, 2).equals("10")) {
            s = "[10]" + s.substring(2);
        } else {
            s = "[" + s.charAt(0) + "]" + s.substring(1);
        }
        return s;
    }
    
    private static boolean closing(String s) {
        return s.isEmpty() || s.equals("]");
    }
    
    // Finds the character position of the closing bracket, given that charAt(0) == '['
    private static int findClosing(String s) {
        int count = 1;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c == '[') {
                count++;
            } else if (c == ']') {
                count--;
            }
            
            if (count == 0) {
                return i;
            }
        }
        return 0;
    }
}
