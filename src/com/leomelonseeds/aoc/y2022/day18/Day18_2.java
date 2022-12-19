package com.leomelonseeds.aoc.y2022.day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day18_2 {
    
    private static Set<List<Integer>> cubes;

    public static void main(String[] args) throws NumberFormatException {
        cubes = new HashSet<>();
        for (String s : Utils.getInput()) {
            String a[] = s.split(",");
            List<Integer> pos = new ArrayList<>();
            for (String num : a) {
                pos.add(Integer.parseInt(num));
            }
            cubes.add(pos);
        }
        
        List<Integer> maxes = new ArrayList<>();
        List<Integer> mins = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            maxes.add(getMax(i));
            mins.add(getMin(i));
        }
        
        // Add big grid of cubes to go down
        Set<List<Integer>> mintomax = new HashSet<>();
        for (int j = mins.get(1); j <= maxes.get(1); j++) {
            for (int k = mins.get(2); k <= maxes.get(2); k++) {
                mintomax.add(Arrays.asList(new Integer[] {mins.get(0), j, k}));
            }
        }
        
        // Iterate min to max, filling cubes along the way
        Set<List<Integer>> filledCubes = new HashSet<>();
        for (int j = mins.get(0); j <= maxes.get(0); j++) {
            for (List<Integer> toAnalyze : mintomax) {
                boolean withinfound = true;
                if (!cubes.stream().anyMatch(cube -> cube.equals(toAnalyze))) {
                    Set<List<Integer>> filler = new HashSet<>();
                    filler.add(toAnalyze);
                    boolean outbounds = false;
                    while (!outbounds) {
                        Set<List<Integer>> extras = new HashSet<>();
                        for (List<Integer> f : new HashSet<>(filler)) {
                            int x = f.get(0);
                            int y = f.get(1);
                            int z = f.get(2);
                            
                            if (x > maxes.get(0) || x < mins.get(0) ||
                                y > maxes.get(1) || y < mins.get(1) ||
                                z > maxes.get(2) || z < mins.get(2)) {
                                outbounds = true;
                                withinfound = false;
                                break;
                            }
                            
                            extras.add(Arrays.asList(new Integer[] {x + 1, y, z}));
                            extras.add(Arrays.asList(new Integer[] {x - 1, y, z}));
                            extras.add(Arrays.asList(new Integer[] {x, y + 1, z}));
                            extras.add(Arrays.asList(new Integer[] {x, y - 1, z}));
                            extras.add(Arrays.asList(new Integer[] {x, y, z + 1}));
                            extras.add(Arrays.asList(new Integer[] {x, y, z - 1}));
                        }
                        
                        for (List<Integer> extra : new HashSet<>(extras)) {
                            if (cubes.contains(extra) || filler.contains(extra)) {
                                extras.remove(extra);
                            }
                        }
                        
                        if (extras.isEmpty()) {
                            break;
                        }
                        
                        filler.addAll(extras);
                    }
                }
                
                if (withinfound) {
                    filledCubes.add(new ArrayList<>(toAnalyze));
                }
                
                toAnalyze.set(0, toAnalyze.get(0) + 1);
            }
        }

        int total = filledCubes.size() * 6;
        for (List<Integer> l : filledCubes) {
            for (List<Integer> l2 : filledCubes) {
                for (int i = 0; i < 3; i++) {
                    List<Integer> ltemp = new ArrayList<>(l);
                    List<Integer> l2temp = new ArrayList<>(l2);
                    int lint = ltemp.remove(i);
                    int l2int = l2temp.remove(i);
                    if (ltemp.equals(l2temp) && Math.abs(lint - l2int) == 1) {
                        total--;
                    }
                }
            }
        }
        
        Utils.println(total);
    }
    
    private static int getMax(int i) {
        int max = 0;
        for (List<Integer> cube : cubes) {
            int cur = cube.get(i);
            if (cur > max) {
                max = cur;
            }
        }
        return max;
    }
    
    private static int getMin(int i) {
        int min = 1000000;
        for (List<Integer> cube : cubes) {
            int cur = cube.get(i);
            if (cur < min) {
                min = cur;
            }
        }
        return min;
    }
}
