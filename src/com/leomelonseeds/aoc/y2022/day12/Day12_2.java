package com.leomelonseeds.aoc.y2022.day12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day12_2 {

    private static List<String> input;
    private static int max = 2000000000;
    private static Map<List<Integer>, Integer> sizes;
    private static int sizex;
    private static int sizey;

    public static void main(String[] args) {
        input = Utils.getInput();
        sizes = new HashMap<>();
        // Replace "S" with "a" and "E" with "{" for reasons
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            s = s.replace('S', 'a');
            s = s.replace('E', '{');
            input.set(i, s);
        }
        sizex = input.get(0).length();
        sizey = input.size();
        travel(new int[] {0, 26}, 'a', new HashSet<>());
        Utils.println(max);
    }

    public static void travel(int[] pos, char last, Set<List<Integer>> travelled) {
        if (pos[0] < 0 || pos[0] >= sizex || pos[1] < 0 || pos[1] >= sizey) {
            return;
        }
        
        List<Integer> asList = Arrays.asList(new Integer[] {pos[0], pos[1]});
        if (!travelled.add(asList)) {
            return;
        }
        
        char c = input.get(pos[1]).charAt(pos[0]);
        if (c - last > 1) {
            return;
        }
        
        // Stop if another path has gotten to this place in less steps
        if (sizes.get(asList) != null && sizes.get(asList) <= travelled.size()) {
            return;
        }
        sizes.put(asList, travelled.size());
        
        if (c == '{') {
            int steps = travelled.size() - 1;
            if (steps < max) {
                max = steps;
            }
            return;
        }

        travel(new int[] {pos[0] + 1, pos[1]}, c, new HashSet<>(travelled));
        travel(new int[] {pos[0] - 1, pos[1]}, c, new HashSet<>(travelled));
        travel(new int[] {pos[0], pos[1] + 1}, c, new HashSet<>(travelled));
        travel(new int[] {pos[0], pos[1] - 1}, c, new HashSet<>(travelled));
    }
}
