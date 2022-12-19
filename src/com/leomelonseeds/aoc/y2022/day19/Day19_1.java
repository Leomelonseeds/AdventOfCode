package com.leomelonseeds.aoc.y2022.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.leomelonseeds.aoc.Utils;

public class Day19_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        int total = 0;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            String a[] = s.split(" ");
            List<Integer> costs = new ArrayList<>();
            for (int j : new int[] {6, 12, 18, 21, 27, 30}) {
                costs.add(Integer.parseInt(a[j]));
            }
            Set<List<Integer>> counts = new HashSet<>();
            counts.add(Arrays.asList(new Integer[] {0, 0, 0, 0, 1, 0, 0, 0}));
            total += getGeodes(1, costs, counts) * (i + 1);
        }
        
        Utils.println(total);
    }
    
    // Costs order: oreorecost, clayorecost, obiorecost, obiclaycost, geodeorecost, geodeobicost
    // Counts order: ore, clay, obsidian, geode, orerobot, clayrobot, obsidianrobot, geoderobot
    private static int getGeodes(int minutes, List<Integer> costs, Set<List<Integer>> counts) {
        // Filter out maps based on max score
        int max = counts.stream().mapToInt(c -> c.get(7)).max().orElse(0);
        counts = counts.stream().filter(c -> c.get(7) == max).collect(Collectors.toSet());
        
        // If 1 minute left, collect ores and output result
        if (minutes == 24) {
            counts.forEach(count -> collectOres(count));
            return counts.stream().mapToInt(c -> c.get(3)).max().orElse(0);
        }
        
        // Attempt to buy new robots
        for (List<Integer> c : new ArrayList<>(counts)) {
            counts.remove(c);
            
            // Always buy geode robots if available
            boolean boughtGeode = false;
            if (c.get(0) >= costs.get(4) && c.get(2) >= costs.get(5)) {
                // Utils.println(c + " got a geode at " + minutes);
                c.set(0, c.get(0) - costs.get(4));
                c.set(2, c.get(2) - costs.get(5));
                boughtGeode = true;
            }
            
            // If conditions not met, iterate through every possible buying scenario
            // At each step, apparently only one robot can be bought. This is dumb.
            // So each robot adds a possibility
            List<List<Integer>> toAdd = new ArrayList<>();
            
            // Doing nothing
            List<Integer> nothing = new ArrayList<>(c);
            collectOres(nothing);
            toAdd.add(nothing);
            
            if (!boughtGeode) {
                // Buying obi only
                if (c.get(0) >= costs.get(2) && c.get(1) >= costs.get(3)) {
                    List<Integer> obionly = new ArrayList<>(c);
                    obionly.set(0, c.get(0) - costs.get(2));
                    obionly.set(1, c.get(1) - costs.get(3));
                    collectOres(obionly);
                    obionly.set(6, c.get(6) + 1);
                    toAdd.add(obionly); 
                }
                
                // Buying clay only
                if (c.get(0) >= costs.get(1)) {
                    List<Integer> clayonly = new ArrayList<>(c);
                    clayonly.set(0, c.get(0) - costs.get(1));
                    collectOres(clayonly);
                    clayonly.set(5, c.get(5) + 1);
                    toAdd.add(clayonly);
                }
                
                // Buying ore only
                if (c.get(0) >= costs.get(0)) {
                    List<Integer> oreonly = new ArrayList<>(c);
                    oreonly.set(0, c.get(0) - costs.get(0));
                    collectOres(oreonly);
                    oreonly.set(4, c.get(4) + 1);
                    toAdd.add(oreonly);
                }
            } else {
                for (List<Integer> add : toAdd) {
                    add.set(7, add.get(7) + 1);
                }  
            }
            
            counts.addAll(toAdd);
        }
        
        return getGeodes(minutes + 1, costs, counts);
    }

    // Collect ores before new robots are added
    private static void collectOres(List<Integer> c) {
        c.set(0, c.get(0) + c.get(4));
        c.set(1, c.get(1) + c.get(5));
        c.set(2, c.get(2) + c.get(6));
        c.set(3, c.get(3) + c.get(7));
    }
}
