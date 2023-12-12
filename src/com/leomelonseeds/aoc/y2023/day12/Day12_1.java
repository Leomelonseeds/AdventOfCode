package com.leomelonseeds.aoc.y2023.day12;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day12_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput("test");
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            String f = s.split(" ")[0];
            List<Integer> sizes = new ArrayList<>();
            for (String size : s.split(" ")[1].split(",")) {
                sizes.add(Integer.parseInt(size));
            }
            
            int exist = 0;
            int quest = 0;
            for (int j = 0; j < f.length(); j++) {
                if (f.charAt(j) == '#') {
                    exist++;
                } else if (f.charAt(j) == '?') {
                    quest++;
                }
            }
            
            int left = sizes.stream().mapToInt(z -> z).sum() - exist;
            
            int[] p = new int[quest];
            for (int j = 0; j < quest; j++) {
                p[j] = j < quest - left ? 0 : 1;
            }
            
            long curSum = 0;
            do {
                int c = 0;
                String cf = f;
                for (int j = 0; j < f.length(); j++) {
                    if (f.charAt(j) != '?') {
                        continue;
                    }
                    
                    cf = cf.substring(0, j) + (p[c] == 0 ? "." : "#") + (j + 1 >= f.length() ? "" : cf.substring(j+1)); 
                    c++;
                }
                
                cf = cf.replaceFirst("\\.++$", "");
                cf = cf.replaceFirst("^\\.+", "");
                String[] v = cf.split("\\.+");
                
                if (v.length != sizes.size()) {
                    continue;
                }
                
                boolean succ = true;
                for (int j = 0; j < v.length; j++) {
                    if (v[j].length() != sizes.get(j)) {
                        succ = false;
                        break;
                    }
                }
                
                curSum = succ ? ++curSum : curSum;
            } while(Utils.findNextPermutation(p));
            
            Utils.println(curSum);
            sum += curSum;
        }
        
        Utils.println(sum);
    }
}
