package com.leomelonseeds.aoc.y2022.day15;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day15_2 {

    public static void main(String[] args) {
        Set<List<Integer>> toSearch = new HashSet<>();
        Map<List<Integer>, Integer> sensors = new HashMap<>();
        int max = 4000000;
        for (String s : Utils.getInput()) {
            String a[] = s.split(" ");
            int sx = Integer.parseInt(a[2].split("=")[1].replace(",", ""));
            int sy = Integer.parseInt(a[3].split("=")[1].replace(":", ""));
            int bx = Integer.parseInt(a[8].split("=")[1].replace(",", ""));
            int by = Integer.parseInt(a[9].split("=")[1]);
            int distance = Math.abs(bx - sx) + Math.abs(by - sy);
            sensors.put(Arrays.asList(new Integer[] {sx, sy}), distance);
            
            // Add 1 to previous distance to find a ring around beacon
            distance++;
            for (int x = sx - distance; x <= sx + distance; x++) {
                int ydiff = distance;
                if (x < sx) {
                    ydiff -= sx - x;
                } else if (x > sx) {
                    ydiff -= x - sx;
                }
                int y1 = sy - ydiff;
                int y2 = sy + ydiff;
                if (x < 0 || y1 < 0 || x > max || y2 > max) {
                    continue;
                }
                Integer[] pos1 = new Integer[] {x, sy - ydiff};
                Integer[] pos2 = new Integer[] {x, sy + ydiff};
                toSearch.add(Arrays.asList(pos1));
                toSearch.add(Arrays.asList(pos2));
            }
        }
        
        for (List<Integer> search : toSearch) {
            int x = search.get(0);
            int y = search.get(1);
            boolean sensorFound = false;
            for (Entry<List<Integer>, Integer> e : sensors.entrySet()) {
                List<Integer> pos = e.getKey();
                int distance = Math.abs(pos.get(0) - x) + Math.abs(pos.get(1) - y);
                if (distance <= e.getValue()) {
                    sensorFound = true;
                    break;
                }
            }
            
            if (!sensorFound) {
                Utils.println((long) x * 4000000 + y);
            }
        }
    }

}
