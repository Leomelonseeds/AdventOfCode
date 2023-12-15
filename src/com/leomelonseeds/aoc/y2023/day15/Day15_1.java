package com.leomelonseeds.aoc.y2023.day15;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day15_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] sargs = input.get(i).split(",");
            for (String s : sargs) {
                int cursum = 0;
                for (int j = 0; j < s.length(); j++) {
                    int ascii = s.charAt(j);
                    cursum += ascii;
                    cursum *= 17;
                    cursum = cursum % 256;
                }
                sum += cursum;
            }
        }
        
        Utils.print(sum);
    }
}
