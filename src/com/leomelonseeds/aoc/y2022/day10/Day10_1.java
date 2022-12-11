package com.leomelonseeds.aoc.y2022.day10;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day10_1 {

    public static void main(String[] args) throws NumberFormatException {
        List<String> input = Utils.getInput();
        int x = 1;
        int count = 0;
        
        int head = 0;
        int cycle = 1;
        int amount = 0;
        for (int i = 0; i <= 220; i++) {
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
            
            if ((i + 1) % 40 == 20) {
                count += (i + 1) * x;
                Utils.print(x + ", ");
                Utils.println((i + 1) * x);
            }
            
            if (cycle == 1) {
                x += amount;
                head++;
            }
        }
        
        Utils.println(count);
    }
}
