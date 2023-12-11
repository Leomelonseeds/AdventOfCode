package com.leomelonseeds.aoc.y2023.day11;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day11_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<Long> br = new ArrayList<>(); // rows w/ no gala
        List<Long> bc = new ArrayList<>(); // colum with no gala
        List<List<Long>> gala = new ArrayList<>();
        for (long i = 0; i < input.size(); i++) {
            // row
            String s = input.get((int) i);
            if (!s.contains("#")) {
                br.add(i);
            } else {
                for (long j = 0; j < s.length(); j++) {
                    if (s.charAt((int) j) == '#') {
                        List<Long> g = new ArrayList<>();
                        g.add(i); // row i
                        g.add(j); // colum j
                        gala.add(g);
                    }
                }
            }
        }
        
        for (long i = 0; i < input.get(0).length(); i++) {
            boolean noGalaxy = true;
            for (long j = 0; j < input.size(); j++) {
                if (input.get((int) j).charAt((int) i) == '#') {
                    noGalaxy = false;
                    break;
                }
            }
            if (noGalaxy) {
                bc.add(i);
            }
        }

        for (long i = 0; i < gala.size(); i++) {
            List<Long> g = gala.get((int) i);
            long r = g.get(0);
            long c = g.get(1);
            long fr = r;
            long fc = c;
            for (long r1 = 0; r1 < r; r1++) {
                if (br.contains(r1)) {
                    fr += 1000000 - 1;
                }
            }
            
            for (long c1 = 0; c1 < c; c1++) {
                if (bc.contains(c1)) {
                    fc += 1000000 - 1;
                }
            }
            
            g.set(0, fr);
            g.set(1, fc);
        }
        
        long sum = 0;
        long k = 0;
        for (long i = 0; i < gala.size(); i++) {
            for (long j = k; j < gala.size(); j++) {
                if (i == j) {
                    continue;
                }
                List<Long> g1 = gala.get((int) i);
                List<Long> g2 = gala.get((int) j);
                sum += Math.abs(g1.get(0) - g2.get(0)) + Math.abs(g1.get(1) - g2.get(1));
            }
            k++;
        }
        
        Utils.println(sum);
    }
}
