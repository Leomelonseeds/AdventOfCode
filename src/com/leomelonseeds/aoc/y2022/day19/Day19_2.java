package com.leomelonseeds.aoc.y2022.day19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.leomelonseeds.aoc.Utils;

public class Day19_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        int total = 1;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            String a[] = s.split(" ");
            List<Integer> costs = new ArrayList<>();
            for (int j : new int[] {6, 12, 18, 21, 27, 30}) {
                costs.add(Integer.parseInt(a[j]));
            }
            Set<List<Integer>> counts = new HashSet<>();
            counts.add(Arrays.asList(new Integer[] {0, 0, 0, 0, 1, 0, 0, 0}));
            Set<List<Integer>> counts2 = new HashSet<>(counts);
            // In this input, blueprint 1 uses alternative filtering method,
            // while for the others the standard max filter works fine.
            total *= Math.max(getGeodes(1, costs, counts, false), getGeodes(1, costs, counts2, true));
        }
        
        Utils.println(total);
    }
    
    // Costs order: oreorecost, clayorecost, obiorecost, obiclaycost, geodeorecost, geodeobicost
    // Counts order: ore, clay, obsidian, geode, orerobot, clayrobot, obsidianrobot, geoderobot
    private static int getGeodes(int minutes, List<Integer> costs, Set<List<Integer>> counts, boolean maxfilter) {
        // Filter out maps based on a few funny assumptions
        if (!maxfilter) {
            if (minutes <= 16) {
                counts = counts.stream().filter(c -> c.get(7) == 0).collect(Collectors.toSet());
            }
            
            if (minutes >= 24) {
                int max = counts.stream().mapToInt(c -> c.get(7)).max().orElse(0);
                counts = counts.stream().filter(c -> c.get(7) >= max - 1).collect(Collectors.toSet());
            }
        } else {
            int max = counts.stream().mapToInt(c -> c.get(7)).max().orElse(0);
            counts = counts.stream().filter(c -> c.get(7) >= max).collect(Collectors.toSet());
        }
        
        // If 1 minute left, collect ores and output result
        if (minutes == 32) {
            counts.forEach(count -> collectOres(count));
            return counts.stream().mapToInt(c -> c.get(3)).max().orElse(0);
        }
        
        Utils.println(minutes);
        
        // Attempt to buy new robots
        for (List<Integer> c : new ArrayList<>(counts)) {
            counts.remove(c);
            // Iterate through every possible buying scenario
            List<List<Integer>> toAdd = new ArrayList<>();
            
            // Doing nothing
            List<Integer> nothing = new ArrayList<>(c);
            collectOres(nothing);
            toAdd.add(nothing);
            
            // Buying geode
            if (c.get(0) >= costs.get(4) && c.get(2) >= costs.get(5)) {
                List<Integer> geode = new ArrayList<>(c);
                geode.set(0, c.get(0) - costs.get(4));
                geode.set(2, c.get(2) - costs.get(5));
                collectOres(geode);
                geode.set(7, c.get(7) + 1);
                toAdd.add(geode);
            }
            
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
            
            counts.addAll(toAdd);
        }
        
        return getGeodes(minutes + 1, costs, counts, maxfilter);
    }

    // Collect ores before new robots are added
    private static void collectOres(List<Integer> c) {
        c.set(0, c.get(0) + c.get(4));
        c.set(1, c.get(1) + c.get(5));
        c.set(2, c.get(2) + c.get(6));
        c.set(3, c.get(3) + c.get(7));
    }
}
