package com.leomelonseeds.aoc.y2023.day13;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day13_2 {

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
            
            long hori = getSmudgeReflect(cb, false);
            long vert = getSmudgeReflect(cbf, true);
            sum += hori == 0 ? vert : hori;
            cb.clear();
            cbf.clear();
        }
        
        Utils.println(sum);
    }
    
    public static long getSmudgeReflect(List<List<Character>> cb, boolean vert) {
        long sum = 0;
        for (int j = 0; j < cb.size() - 1; j++) {
            int left = 0;
            int right = 0;
            boolean offbyone = false;
            boolean succ = true;
            for (int k = 0; k <= Math.min(j, cb.size() - j - 2); k++) {
                left = Utils.toBinary(cb.get(j - k), '#');
                right = Utils.toBinary(cb.get(j + 1 + k), '#');
                int r = left ^ right;
                if (r == 0) {
                    continue;
                }
                
                if ((r & (r - 1)) == 0) {
                    if (offbyone) {
                        succ = false;
                        break;
                    } else {
                        offbyone = true;
                    }
                } else {
                    succ = false;
                    break;
                }
            }
            
            if (offbyone && succ) {
                sum += (j + 1) * (vert ? 1 : 100);
            }
        }
        
        return sum;
    }
}
