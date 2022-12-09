package com.leomelonseeds.aoc2022;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9_1 {

    public static void main(String[] args) throws NumberFormatException {
        Set<List<Integer>> positions = new HashSet<>();
        positions.add(Arrays.asList(new Integer[] {0, 0}));
        int[] head = {0, 0};
        int[] tail = {0, 0};
        for (String s : Utils.getInput()) {
            String a[] = s.split(" ");
            String dir = a[0];
            int n = Integer.parseInt(a[1]);
            for (int i = 0; i < n; i++) {
                switch (dir) {
                case "R":
                    head[0]++;
                    break;
                case "L":
                    head[0]--;
                    break;
                case "U":
                    head[1]++;
                    break;
                case "D":
                    head[1]--;
                }   
                
                // Check touching
                if (Math.abs(head[0] - tail[0]) <= 1 && Math.abs(head[1] - tail[1]) <= 1) {
                    continue;
                }
                
                // Check same column
                if (head[0] == tail[0]) {
                    // Head above tail
                    if (head[1] - tail[1] > 0) {
                        tail[1]++;
                    } else {
                        tail[1]--;
                    }
                }
                
                // Check same row
                else if (head[1] == tail[1]) {
                    // Head to the right of tail
                    if (head[0] - tail[0] > 0) {
                        tail[0]++;
                    } else {
                        tail[0]--;
                    }
                }
                
                // Must be diag and apart
                else {
                    if (Math.abs(head[1] - tail[1]) == 2) {
                        tail[1] += (head[1] - tail[1]) / 2;
                        tail[0] = head[0];
                    } else {
                        tail[0] += (head[0] - tail[0]) / 2;
                        tail[1] = head[1];
                    }
                }
                

                positions.add(Arrays.asList(new Integer[] {tail[0], tail[1]}));
            }
        }
        
        Utils.println(positions.size());
    }
}
