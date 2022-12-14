package com.leomelonseeds.aoc.y2022.day14;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day14_2 {

    public static void main(String[] args) throws NumberFormatException {
        Set<List<Integer>> rocks = new HashSet<>();
        for (String s : Utils.getInput()) {
            String[] positions = s.split(" -> ");
            for (int i = 0; i < positions.length - 1; i++) {
                String[] pos1 = positions[i].split(",");
                String[] pos2 = positions[i + 1].split(",");
                int posi = pos1[0].equals(pos2[0]) ? 1 : 0;
                int j1 = Integer.parseInt(pos1[posi]);
                int j2 = Integer.parseInt(pos2[posi]);
                for (int j = Math.min(j1, j2); j <= Math.max(j1, j2); j++) {
                    Integer[] pos;
                    if (posi == 0) {
                        pos = new Integer[] {j, Integer.parseInt(pos1[1])};
                    } else {
                        pos = new Integer[] {Integer.parseInt(pos1[0]), j};
                    }
                    rocks.add(Arrays.asList(pos));
                }
            }
        }
        
        int maxDepth = 0;
        for (List<Integer> pos : rocks) {
            int y = pos.get(1);
            if (y > maxDepth) {
                maxDepth = y;
            }
        }
        
        for (int i = -10000; i <= 10000; i++) {
            rocks.add(Arrays.asList(new Integer[] {i, maxDepth + 2}));
        }
        
        int sands = 0;
        while (true) {
            Integer[] sand = new Integer[] {500, 0};
            if (rocks.contains(Arrays.asList(sand))) {
                break;
            }
            while (true) {
                int x = sand[0];
                int y = sand[1];
                if (!rocks.contains(Arrays.asList(new Integer[] {x, y + 1}))) {
                    sand[1] = y + 1;
                } else if (!rocks.contains(Arrays.asList(new Integer[] {x - 1, y + 1}))) {
                    sand[0] = x - 1;
                    sand[1] = y + 1;
                } else if (!rocks.contains(Arrays.asList(new Integer[] {x + 1, y + 1}))) {
                    sand[0] = x + 1;
                    sand[1] = y + 1;
                } else {
                    rocks.add(Arrays.asList(sand));
                    sands++;
                    break;
                }
            }
        }
        
        Utils.println(sands);
    }
}
