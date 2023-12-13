package com.leomelonseeds.aoc.y2023.day13;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day13_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        input.add("");
        long sum = 0;
        List<List<Character>> cb = new ArrayList<>();
        List<List<Character>> cbf = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            if (!s.isEmpty()) {
                List<Character> cs = new ArrayList<>();
                for (int j = 0; j < s.length(); j++) {
                    cs.add(s.charAt(j));
                }
                cb.add(cs);
                continue;
            }
            
            for (int j = 0; j < cb.get(0).size(); j++) {
                List<Character> cfs = new ArrayList<>();
                for (int k = 0; k < cb.size(); k++) {
                    cfs.add(cb.get(k).get(j));
                }
                
                cbf.add(cfs);
            }
            
            long hori = getReflects(cb, false);
            long vert = getReflects(cbf, true);
            sum += hori == 0 ? vert : hori;
            cb.clear();
            cbf.clear();
        }
        
        Utils.println(sum);
    }
    
    public static long getReflects(List<List<Character>> cb, boolean vert) {
        long sum = 0;
        for (int j = 0; j < cb.size() - 1; j++) {
            if (cb.get(j).equals(cb.get(j + 1))) {
                boolean success = true;
                for (int k = 1; k <= Math.min(j, cb.size() - j - 2); k++) {
                    if (!cb.get(j - k).equals(cb.get(j + 1 + k))) {
                        success = false;
                        break;
                    }
                }
                
                if (success) {
                    sum += (j + 1) * (vert ? 1 : 100);
                    break; // no double reflections in one thing?
                }
            }
        }
        
        return sum;
    }
}
