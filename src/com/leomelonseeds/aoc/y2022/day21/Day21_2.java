package com.leomelonseeds.aoc.y2022.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leomelonseeds.aoc.Utils;

public class Day21_2 {
    
    private static Map<String, String> monkeys;

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        monkeys = new HashMap<>();
        for (String s : input) {
            String a[] = s.split(": ");
            monkeys.put(a[0], a[1]);
        }
        
        Utils.println(reverseNumber("zhms", getNumber("qqqz")));
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
    
    private static boolean containsHumn(String monkey) {
        if (monkey.equals("humn")) {
            return true;
        }
        
        String word = monkeys.get(monkey);
        String args[] = word.split(" ");
        if (args.length == 1) {
            return false;
        }
        
        return (containsHumn(args[0]) || containsHumn(args[2]));
    }
    
    private static long reverseNumber(String monkey, long current) {
        String word = monkeys.get(monkey);
        String args[] = word.split(" ");
        if (args.length == 1) {
            return current;
        }
        
        String nextMonkey;
        String num;
        boolean left;
        if (containsHumn(args[0])) {
            left = true;
            nextMonkey = args[0];
            num = args[2];
        } else {
            left = false;
            nextMonkey = args[2];
            num = args[0];
        } 
        
        switch (args[1]) {
        case "+":
            return reverseNumber(nextMonkey, current - getNumber(num));
        case "-":
            return left ? reverseNumber(nextMonkey, current + getNumber(num)) : 
                reverseNumber(nextMonkey, getNumber(num) - current);
        case "*":
            return reverseNumber(nextMonkey, current / getNumber(num)); 
        default:
            return left ? reverseNumber(nextMonkey, current * getNumber(num)) : 
                reverseNumber(nextMonkey, getNumber(num) / current);
        }
    }
}
