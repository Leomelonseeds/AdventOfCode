package com.leomelonseeds.aoc.y2022.day13;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day13_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        for (int i = input.size() - 1; i >= 0; i--) {
            if (input.get(i).isBlank()) {
                input.remove(i);
            }
        }
        
        input.add("[[2]]");
        input.add("[[6]]");
        Collections.sort(input, comparator);
        
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).equals("[[2]]")) {
                i1 = i + 1;
            } else if (input.get(i).equals("[[6]]")) {
                i2 = i + 1;
            }
        }
        
        Utils.println(i1 * i2);
    }
    
    private static Comparator<String> comparator = new Comparator<>() {
        @Override
        public int compare(String left, String right) {
            if (pcompare(left, right)) {
                return -1;
            }
            return 1;
        }
    };
    
    // Returns true if items are in right order
    private static Boolean pcompare(String left, String right) {
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
            Boolean compared = pcompare(left.substring(1, leftClosing), right.substring(1, rightClosing));
            if (compared != null) {
                return compared;
            }
            left = left.substring(leftClosing);
            right = right.substring(rightClosing);
        } else if (leftArray && !rightArray) {
            return pcompare(left, withBrackets(right));
        } else if (!leftArray && rightArray) {
            return pcompare(withBrackets(left), right);
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
        return pcompare(left.substring(l == 10 ? 2 : 1), right.substring(l == 10 ? 2 : 1));
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
