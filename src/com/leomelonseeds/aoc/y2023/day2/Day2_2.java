package com.leomelonseeds.aoc.y2023.day2;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day2_2 {

    public static void main(String[] args) {
        int sum = 0;
        List<String> input = Utils.getInput();
        for (int i = 0; i < input.size(); i++) {
            int r = 0;
            int g = 0;
            int b = 0;
            String s = input.get(i);
            String[] sargs = s.split("; ");
            sargs[0] = sargs[0].split(": ")[1];
            for (String arg : sargs) {
                String[] cubes = arg.split(", ");
                for (String carg : cubes) {
                    int num = Integer.parseInt(carg.split(" ")[0]);
                    String color = carg.split(" ")[1];
                    if (color.equals("red") && num > r) {
                        r = num;
                    } else if (color.equals("blue") && num > b) {
                        b = num;
                    } else if (color.equals("green") && num > g) {
                        g = num;
                    }
                }
            }
            sum += r*g*b;
        }
        
        Utils.println(sum);
    }

}
