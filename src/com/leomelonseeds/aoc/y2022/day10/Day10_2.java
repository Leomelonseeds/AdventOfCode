package com.leomelonseeds.aoc.y2022.day10;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day10_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        int x = 1;
        
        int head = 0;
        int cycle = 1;
        int amount = 0;
        for (int i = 0; i < 240; i++) {
            cycle--;
            
            if (cycle == 0) {
                String s = input.get(head);
                String a[] = s.split(" ");
                if (a[0].equals("addx")) {
                    cycle = 2;
                    amount = Integer.parseInt(a[1]);
                } else {
                    cycle = 1;
                    amount = 0;
                }
            }
            
            if (i % 40 == 0) {
                Utils.println();
            }
            
            if (x + 1 >= i % 40 && i % 40 >= x - 1) {
                Utils.print("#");
            } else {
                Utils.print(".");
            }
            
            if (cycle == 1) {
                x += amount;
                head++;
            }
        }
    }

}
