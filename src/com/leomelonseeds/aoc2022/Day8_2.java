package com.leomelonseeds.aoc2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        int highest = 0;
        
        for (int i = 0; i < input.size(); i++) {
            String current = input.get(i);
            for (int j = 0; j < current.length(); j++) {
                List<Integer> left = new ArrayList<>();
                List<Integer> right = new ArrayList<>();
                List<Integer> up = new ArrayList<>();
                List<Integer> down = new ArrayList<>();
                
                for (int k = 0; k < j; k++) {
                    left.add(Character.getNumericValue(current.charAt(k)));
                }
                
                for (int k = j + 1; k < current.length(); k++) {
                    right.add(Character.getNumericValue(current.charAt(k)));
                }
                
                for (int l = 0; l < i; l++) {
                    up.add(Character.getNumericValue(input.get(l).charAt(j)));
                }
                
                for (int l = i + 1; l < input.size(); l++) {
                    down.add(Character.getNumericValue(input.get(l).charAt(j)));
                }
                
                int n = Character.getNumericValue(current.charAt(j));
                Collections.reverse(left);
                Collections.reverse(up);
                
                int score = countVisible(left, n) * countVisible(up, n) * 
                            countVisible(right, n) * countVisible(down, n);
                if (score > highest) {
                    highest = score;
                }
            }
        }

        Utils.println(highest);
    }
    
    public static int countVisible(List<Integer> l, int max) {
        int count = 0;
        for (int i : l) {
            count++;
            if (i >= max) {
                break;
            }
        }
        return count;
    }
}
