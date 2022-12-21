package com.leomelonseeds.aoc.y2022.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leomelonseeds.aoc.Utils;

public class Day21_1 {
    
    private static Map<String, String> monkeys;

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        monkeys = new HashMap<>();
        for (String s : input) {
            String a[] = s.split(": ");
            monkeys.put(a[0], a[1]);
        }
        
        Utils.println(getNumber("root"));
    }
    
    private static long getNumber(String monkey) {
        String word = monkeys.get(monkey);
        String args[] = word.split(" ");
        if (args.length == 1) {
            return Integer.parseInt(args[0]);
        }
        
        String left = args[0];
        String right = args[2];
        switch (args[1]) {
        case "+":
            return getNumber(left) + getNumber(right);
        case "-":
            return getNumber(left) - getNumber(right);
        case "*":
            return getNumber(left) * getNumber(right);
        default:
            return getNumber(left) / getNumber(right);
        }
    }
}
