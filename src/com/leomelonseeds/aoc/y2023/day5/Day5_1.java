package com.leomelonseeds.aoc.y2023.day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day5_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<Long> seeds = new ArrayList<>();
        List<Boolean> done = new ArrayList<>();
        String[] s1 = input.get(0).split(" ");
        for (int i = 1; i < s1.length; i++) {
            seeds.add(Long.parseLong(s1[i]));
            done.add(false);
        }
        
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i).isBlank()) {
                Collections.fill(done, false);
                continue;
            }
            
            String[] snums = input.get(i).split(" ");
            if (!Utils.isDigit(snums[0])) {
                continue;
            }
            
            long[] nums = {Long.parseLong(snums[0]), Long.parseLong(snums[1]), Long.parseLong(snums[2])};
            
            for (int j = 0; j < seeds.size(); j++) {
                long seed = seeds.get(j);
                if (!done.get(j) && seed >= nums[1] && seed < nums[1] + nums[2]) {
                    seeds.set(j, seed - (nums[1] - nums[0]));
                    done.set(j, true);
                }
            }
        }
        
        Utils.println(seeds.stream().mapToLong(s -> s).min().getAsLong());
    }

}
