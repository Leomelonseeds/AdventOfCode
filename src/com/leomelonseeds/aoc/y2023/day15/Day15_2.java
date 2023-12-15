package com.leomelonseeds.aoc.y2023.day15;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day15_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<List<Lens>> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new ArrayList<>());
        }
        
        for (int i = 0; i < input.size(); i++) {
            String[] sargs = input.get(i).split(",");
            for (String s : sargs) {
                String[] largs = s.split("=|-");
                String lens = largs[0];
                int box = hash(lens);
                List<Lens> cb = boxes.get(box);
                Lens temp = new Lens(lens, 0);
                if (largs.length > 1) {
                    int len = Integer.parseInt(largs[1]);
                    if (cb.contains(temp)) {
                        cb.get(cb.indexOf(temp)).setlength(len);
                    } else {
                        cb.add(new Lens(lens, len));
                    }
                } else {
                    cb.remove(temp);
                }
            }
            
        }
        
        long sum = 0;
        for (int i = 0; i < 256; i++) {
            List<Lens> cb = boxes.get(i);
            for (int j = 0; j < cb.size(); j++) {
                sum += (1 + i) * (1 + j) * cb.get(j).length();
            }
        }
        
        Utils.println(sum);
    }
    
    public static int hash(String s) {
        int cursum = 0;
        for (int j = 0; j < s.length(); j++) {
            int ascii = s.charAt(j);
            cursum += ascii;
            cursum *= 17;
            cursum = cursum % 256;
        }
        return cursum;
    }
}
