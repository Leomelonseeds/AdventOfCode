package com.leomelonseeds.aoc.y2022.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day9_2 {

    public static void main(String[] args) throws NumberFormatException {
        Set<List<Integer>> positions = new HashSet<>();
        positions.add(Arrays.asList(new Integer[] {0, 0}));
        List<int[]> tails = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tails.add(new int[] {0, 0});
        }
        int[] head = {0, 0};
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
                
                int[] prevTail = {head[0], head[1]};
                for (int[] tail : tails) {
                    // Check touching
                    if (Math.abs(prevTail[0] - tail[0]) <= 1 && Math.abs(prevTail[1] - tail[1]) <= 1) {
                        prevTail[0] = tail[0];
                        prevTail[1] = tail[1];
                        continue;
                    }
                    
                    // Check same column
                    if (prevTail[0] == tail[0]) {
                        // Head above tail
                        if (prevTail[1] - tail[1] > 0) {
                            tail[1]++;
                        } else {
                            tail[1]--;
                        }
                    }
                    
                    // Check same row
                    else if (prevTail[1] == tail[1]) {
                        // Head to the right of tail
                        if (prevTail[0] - tail[0] > 0) {
                            tail[0]++;
                        } else {
                            tail[0]--;
                        }
                    }
                    
                    // Must be diag and apart
                    else {
                        if (Math.abs(prevTail[0] - tail[0]) == 2 && Math.abs(prevTail[1] - tail[1]) == 2) {
                            tail[1] += (prevTail[1] - tail[1]) / 2;
                            tail[0] += (prevTail[0] - tail[0]) / 2;
                        } else if (Math.abs(prevTail[1] - tail[1]) == 2) {
                            tail[1] += (prevTail[1] - tail[1]) / 2;
                            tail[0] = prevTail[0];
                        } else {
                            tail[0] += (prevTail[0] - tail[0]) / 2;
                            tail[1] = prevTail[1];
                        }
                    }
                    
                    prevTail[0] = tail[0];
                    prevTail[1] = tail[1];
                }
                
                positions.add(Arrays.asList(new Integer[] {tails.get(8)[0], tails.get(8)[1]}));
            }
        }

        Utils.println(positions.size());
    }
}
