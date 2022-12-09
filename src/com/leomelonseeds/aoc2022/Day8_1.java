package com.leomelonseeds.aoc2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8_1 {

    public static void main(String[] args) throws NumberFormatException {
        // Positions of visible trees, coordinates at top left being 0,0
        Set<List<Integer>> positions = new HashSet<>();
        List<String> input = Utils.getInput();
        
        for (int i = 0; i < input.size(); i++) {
            // Left to right
            String current = input.get(i);
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < current.length(); j++) {
                line.add(Character.getNumericValue(current.charAt(j)));
            }
            for (int k : countVisible(line)) {
                positions.add(Arrays.asList(new Integer[] {k, i}));
            }
            
            // Right to left
            Collections.reverse(line);
            for (int m : countVisible(line)) {
                positions.add(Arrays.asList(new Integer[] {current.length() - 1 - m, i}));
            }
        }
        
        for (int i = 0; i < input.get(0).length(); i++) {
            // Top to bottom
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < input.size(); j++) {
                String current = input.get(j);
                line.add(Character.getNumericValue(current.charAt(i)));
            }
            for (int k : countVisible(line)) {
                positions.add(Arrays.asList(new Integer[] {i, k}));
            }
            
            // Bottom to top
            Collections.reverse(line);
            for (int m : countVisible(line)) {
                positions.add(Arrays.asList(new Integer[] {i, input.size() - 1 - m}));
            }
        }

        Utils.println(positions.size());
    }
    
    public static List<Integer> countVisible(List<Integer> l) {
        List<Integer> result = new ArrayList<>();
        int last = -1;
        for (int i = 0; i < l.size(); i++) {
            int current = l.get(i);
            if (current > last) {
                result.add(i);
                last = current;
            }
        }
        return result;
    }
}
