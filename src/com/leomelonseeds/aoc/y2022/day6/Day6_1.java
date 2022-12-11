package com.leomelonseeds.aoc.y2022.day6;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day6_1 {

    public static void main(String[] args) {
        String s = Utils.getInput().get(0);
        for (int i = 0; i < s.length() - 4; i++) {
            String current = s.substring(i, i + 4);
            List<Character> temp = new ArrayList<>();
            boolean success = true;
            for (char c : current.toCharArray()) {
                if (temp.contains(c)) {
                    success = false;
                    break;
                }
                temp.add(c);
            }
            if (success) {
                Utils.print(i + 4);
                return;
            }
        }
    }

}
