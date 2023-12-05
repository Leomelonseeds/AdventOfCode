package com.leomelonseeds.aoc.y2023.day5;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day5_2 {
    
    public static void main(String[] args) {
        
        // THIS IS SO DUMB I WAITED FOR LIKE 10 MINUTES AND IT WORKED HAHHAHAHA
        
        List<String> input = Utils.getInput();
        List<Long> seeds = new ArrayList<>();
        List<Boolean> done = new ArrayList<>();
        String[] s1 = input.get(0).split(" ");
        for (int i = 1; i < s1.length; i++) {
            seeds.add(Long.parseLong(s1[i]));
            done.add(false);
        }
        
        boolean success = false;
        long test = 0;
        long curSeed = 0;
        boolean curDone = false;
        while (!success) {
            test++;
            curSeed = test;
            
            for (int i = input.size() - 1; i >= 0; i--) {
                if (input.get(i).isBlank()) {
                    curDone = false;
                    continue;
                }
                
                if (curDone) {
                    continue;
                }
                
                String[] snums = input.get(i).split(" ");
                if (!Utils.isDigit(snums[0])) {
                    continue;
                }
                
                long[] nums = {Long.parseLong(snums[0]), Long.parseLong(snums[1]), Long.parseLong(snums[2])};
                if (!curDone && curSeed >= nums[0] && curSeed < nums[0] + nums[2]) {
                    curSeed += nums[1] - nums[0];
                    curDone = true;
                }
            } 
            
            // Check if curSeed in range
            for (int i = 0; i < seeds.size(); i += 2) {
                if (curSeed >= seeds.get(i) && curSeed < seeds.get(i) + seeds.get(i+1)) {
                    success = true;
                    break;
                }
            }
            
            if (test % 100000 == 0) {
                Utils.println(test + ": " + curSeed);
            }
        }
        
        Utils.println(test);
    }
}
