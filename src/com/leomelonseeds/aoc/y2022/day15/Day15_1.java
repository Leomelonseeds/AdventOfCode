package com.leomelonseeds.aoc.y2022.day15;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day15_1 {

    public static void main(String[] args) throws NumberFormatException {
        Set<Integer> previousx = new HashSet<>();
        Set<List<Integer>> beacons = new HashSet<>();
        int y = 2000000;
        int count = 0;
        for (String s : Utils.getInput()) {
            String a[] = s.split(" ");
            int sx = Integer.parseInt(a[2].split("=")[1].replace(",", ""));
            int sy = Integer.parseInt(a[3].split("=")[1].replace(":", ""));
            int bx = Integer.parseInt(a[8].split("=")[1].replace(",", ""));
            int by = Integer.parseInt(a[9].split("=")[1]);
            beacons.add(Arrays.asList(new Integer[] {bx, by}));
            int distance = Math.abs(bx - sx) + Math.abs(by - sy);
            for (int x = sx - distance; x <= sx + distance; x++) {
                int ydiff = distance;
                if (x < sx) {
                    ydiff -= sx - x;
                } else if (x > sx) {
                    ydiff -= x - sx;
                }
                if (sy - ydiff > y || y > sy + ydiff) {
                    continue;
                }
                Integer[] pos = new Integer[] {x, y};
                if (beacons.contains(Arrays.asList(pos))) {
                    continue;
                }
                if (!previousx.add(x)) {
                    continue;
                }
                count++;
            }
        }
        
        Utils.println(count);
    }
}
