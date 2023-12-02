package com.leomelonseeds.aoc.y2023.day2;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day2_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        int r = 12;
        int g = 13;
        int b = 14;
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            boolean succ = true;
            String s = input.get(i);
            String[] sargs = s.split("; ");
            sargs[0] = sargs[0].split(": ")[1];
            for (String arg : sargs) {
                String[] cubes = arg.split(", ");
                for (String carg : cubes) {
                    int num = Integer.parseInt(carg.split(" ")[0]);
                    String color = carg.split(" ")[1];
                    if (color.equals("blue") && num > b ||
                        color.equals("green") && num > g ||
                        color.equals("red") && num > r) {
                        succ = false;
                        break;
                    }
                }
                
                if (!succ) {
                    break;
                }
            }
            
            if (succ) {
                sum += i + 1;
            }
        }
        
        Utils.println(sum);
    }

}
