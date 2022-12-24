package com.leomelonseeds.aoc.y2022.day24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day24_1 {
    
    private static List<Integer> start;
    private static List<Integer> end;
    private static List<Blizzard> bs;
    public static int MAX_X;
    public static int MAX_Y;

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        bs = new ArrayList<>();
        MAX_X = input.get(0).length() - 1;
        MAX_Y = input.size() - 1;
        start = Arrays.asList(new Integer[] {1, 0});
        end = Arrays.asList(new Integer[] {MAX_X - 1, MAX_Y});
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == 'v' || c == '>' || c == '<' || c == '^') {
                    bs.add(new Blizzard(c, Arrays.asList(new Integer[] {j, i})));
                }
            }
        }
        
        Set<List<Integer>> init = new HashSet<>();
        init.add(start);
        int result = travel(1, init);
        Utils.println(result);
    }
    
    private static int travel(int minute, Set<List<Integer>> positions) {
        // Move blizzards
        bs.forEach(b -> b.move());
        
        // Travel
        for (List<Integer> pos : new HashSet<>(positions)) {
            int x = pos.get(0);
            int y = pos.get(1);
            Set<List<Integer>> new_pos = new HashSet<>();
            new_pos.add(pos);
            new_pos.add(Arrays.asList(new Integer[] {x + 1, y}));
            new_pos.add(Arrays.asList(new Integer[] {x - 1, y}));
            new_pos.add(Arrays.asList(new Integer[] {x, y + 1}));
            new_pos.add(Arrays.asList(new Integer[] {x, y - 1}));
            for (List<Integer> np : new HashSet<>(new_pos)) {
                if (np.equals(end)) {
                    return minute;
                }
                int nx = np.get(0);
                int ny = np.get(1);
                if (nx <= 0 || nx >= MAX_X || ny <= 0 || ny >= MAX_Y ||
                        bs.stream().anyMatch(b -> b.getPos().equals(np))) {
                    new_pos.remove(np);
                }
            }
            
            positions.remove(pos);
            positions.addAll(new_pos);
        }
        
        return travel(minute + 1, positions);
    }
}
