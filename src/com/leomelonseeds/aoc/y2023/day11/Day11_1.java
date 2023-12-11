package com.leomelonseeds.aoc.y2023.day11;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day11_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<Integer> br = new ArrayList<>(); // rows w/ no gala
        List<Integer> bc = new ArrayList<>(); // colum with no gala
        List<List<Integer>> gala = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            // row
            String s = input.get(i);
            if (!s.contains("#")) {
                br.add(i);
            } else {
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '#') {
                        List<Integer> g = new ArrayList<>();
                        g.add(i); // row i
                        g.add(j); // colum j
                        gala.add(g);
                    }
                }
            }
        }
        
        for (int i = 0; i < input.get(0).length(); i++) {
            boolean noGalaxy = true;
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j).charAt(i) == '#') {
                    noGalaxy = false;
                    break;
                }
            }
            if (noGalaxy) {
                bc.add(i);
            }
        }

        for (int i = 0; i < gala.size(); i++) {
            List<Integer> g = gala.get(i);
            int r = g.get(0);
            int c = g.get(1);
            int fr = r;
            int fc = c;
            for (int r1 = 0; r1 < r; r1++) {
                if (br.contains(r1)) {
                    fr++;
                }
            }
            
            for (int c1 = 0; c1 < c; c1++) {
                if (bc.contains(c1)) {
                    fc++;
                }
            }
            
            g.set(0, fr);
            g.set(1, fc);
        }
        
        int sum = 0;
        int k = 0;
        for (int i = 0; i < gala.size(); i++) {
            for (int j = k; j < gala.size(); j++) {
                if (i == j) {
                    continue;
                }
                List<Integer> g1 = gala.get(i);
                List<Integer> g2 = gala.get(j);
                sum += Math.abs(g1.get(0) - g2.get(0)) + Math.abs(g1.get(1) - g2.get(1));
            }
            k++;
        }
        
        Utils.println(sum);
    }
}
