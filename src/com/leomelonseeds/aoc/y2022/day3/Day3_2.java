package com.leomelonseeds.aoc.y2022.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day3_2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> currentBag = new ArrayList<>();
        int count = 0;
        for (String s : Utils.getInput()) {
            currentBag.add(s);
            if (currentBag.size() < 3) {
                continue;
            }
            char c = findSimilar(currentBag);
            if (Character.isUpperCase(c)) {
                count += c - 38;
            } else {
                count += c - 96;
            }
            currentBag.clear();
        }
        
        Utils.println(count);
    }
    
    public static char findSimilar(List<String> l) {
        String a = l.get(0);
        for (char c : a.toCharArray()) {
            if (l.get(1).contains(c + "") && l.get(2).contains(c + "")) {
                return c;
            }
        }
        return 'L';
    }
}
