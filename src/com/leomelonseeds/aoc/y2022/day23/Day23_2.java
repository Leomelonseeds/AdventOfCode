package com.leomelonseeds.aoc.y2022.day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day23_2 {
    
    // Elves, represented by a position, and its next proposed move
    // If the value is null, then the elf will not move
    private static Map<List<Integer>, List<Integer>> elves;

    public static void main(String[] args) {
        elves = new HashMap<>();
        // Direction. 0 = N, 1 = S, 2 = W, 3 = E
        List<Integer> dir = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            dir.add(i);
        }
        List<String> input = Utils.getInput();
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '#') {
                    elves.put(Arrays.asList(new Integer[] {j, i}), null);
                }
            }
        }
        
        int i = 0;
        while (true) {
            i++;
            // Generate proposals
            for (List<Integer> elf : elves.keySet()) {
                // Check if adjacent squares empty
                boolean empty = true;
                int x = elf.get(0);
                int y = elf.get(1);
                for (int j = x - 1; j <= x + 1; j++) {
                    for (int k = y - 1; k <= y + 1; k++) {
                        if (x == j && y == k) {
                            continue;
                        }
                        if (contains(j, k)) {
                            empty = false;
                            break;
                        }
                    }
                    if (!empty) {
                        break;
                    }
                }
                if (empty) {
                    continue;
                }
                
                for (int d : dir) {
                    boolean found = true;
                    switch (d) {
                    case 0:
                        for (int j = x - 1; j <= x + 1; j++) {
                            if (contains(j, y - 1)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            elves.put(elf, Arrays.asList(new Integer[] {x, y - 1}));
                        }
                        break;
                    case 1:
                        for (int j = x - 1; j <= x + 1; j++) {
                            if (contains(j, y + 1)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            elves.put(elf, Arrays.asList(new Integer[] {x, y + 1}));
                        }
                        break;
                    case 2:
                        for (int k = y - 1; k <= y + 1; k++) {
                            if (contains(x - 1, k)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            elves.put(elf, Arrays.asList(new Integer[] {x - 1, y}));
                        }
                        break;
                    case 3:
                        for (int k = y - 1; k <= y + 1; k++) {
                            if (contains(x + 1, k)) {
                                found = false;
                                break;
                            }
                        }
                        if (found) {
                            elves.put(elf, Arrays.asList(new Integer[] {x + 1, y}));
                        }
                    } 
                    
                    if (found) {
                        break;
                    }
                }
            }
            
            // Check if move possible
            if (elves.values().stream().allMatch(l -> l == null)) {
                break;
            }
            
            // Move elves
            Set<List<Integer>> immovable = new HashSet<>();
            for (Entry<List<Integer>, List<Integer>> e : new HashMap<>(elves).entrySet()) {
                List<Integer> pos = e.getKey();
                List<Integer> propose = e.getValue();
                if (propose == null || immovable.contains(pos)) {
                    elves.put(pos, null);
                    continue;
                }
                
                elves.remove(pos);
                if (elves.values().contains(propose)) {
                    elves.put(pos, null);
                    for (Entry<List<Integer>, List<Integer>> e2 : elves.entrySet()) {
                        if (e2.getValue() != null && e2.getValue().equals(propose)) {
                            immovable.add(e2.getKey());
                        }
                    }
                } else {
                    elves.put(propose, null);
                }
            }
            
            // Change direction
            dir.add(dir.remove(0));
        }
        
        int minx = 0;
        int maxx = 0;
        int miny = 0;
        int maxy = 0;
        for (List<Integer> elf : elves.keySet()) {
            int x = elf.get(0);
            int y = elf.get(1);
            if (x > maxx) {
                maxx = x;
            } else if (x < minx) {
                minx = x;
            }
            
            if (y > maxy) {
                maxy = y;
            } else if (y < miny) {
                miny = y;
            }
        }
        
        // Insane visualization
        for (int y = miny; y <= maxy; y++) {
            for (int x = minx; x <= maxx; x++) {
                if (contains(x, y)) {
                    Utils.print("#");
                } else {
                    Utils.print(".");
                }
            }
            Utils.println();
        }
        
        Utils.println(i);
    }
    
    // Checks if an elf has coordinates x y
    private static boolean contains(int x, int y) {
        return elves.containsKey(Arrays.asList(new Integer[] {x, y}));
    }
}
