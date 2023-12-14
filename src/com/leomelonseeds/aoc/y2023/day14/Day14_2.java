package com.leomelonseeds.aoc.y2023.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day14_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<List<Character>> grid = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            List<Character> g = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                g.add(s.charAt(j));
            }
            grid.add(g);
        }
        
        List<Integer> sums = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            // North roll
            grid = Utils.verticalList(grid);
            roll(grid);
            
            // West
            grid = Utils.verticalList(grid);
            roll(grid);

            // South
            grid = Utils.verticalList(grid);
            for (int j = 0; j < grid.size(); j++) {
                Collections.reverse(grid.get(j));
            }
            roll(grid);
            
            
            // East
            for (int j = 0; j < grid.size(); j++) {
                Collections.reverse(grid.get(j));
            }
            grid = Utils.verticalList(grid);
            for (int j = 0; j < grid.size(); j++) {
                Collections.reverse(grid.get(j));
            }
            roll(grid);
            
            // Reset
            for (int j = 0; j < grid.size(); j++) {
                Collections.reverse(grid.get(j));
            }
            
            int load = getNLoad(grid);
            if (sums.contains(load)) {
                int check = 1000000000;
                int modi = sums.indexOf(load);
                int ac = check - modi - 1;
                int mod = i - modi;
                Utils.println(sums.get(modi + (ac % mod)));
                break;
            }
            sums.add(load);
        }
    }
    
    public static int getNLoad(List<List<Character>> ffg) {
        int sum = 0;
        List<List<Character>> fg = Utils.verticalList(ffg);
        for (List<Character> tg : fg) {
            for (int i = 0; i < tg.size(); i++) {
                if (tg.get(i) == 'O') {
                    sum += tg.size() - i;
                }
            }
        }
        return sum;
    }
    
    public static void roll(List<List<Character>> fg) {
        for (List<Character> tg : fg) {
            for (int i = 0; i < tg.size(); i++) {
                char cur = tg.get(i);
                if (cur != 'O') {
                    continue;
                }
                
                for (int j = i - 1; j >= 0; j--) {
                    if (tg.get(j) == '.') {
                        if (j == 0) {
                            tg.set(j, 'O');
                            tg.set(i, '.');
                            break;
                        } else {
                            continue;
                        }
                    }
                    
                    if (tg.get(j + 1) == '.') {
                        tg.set(i, '.');
                        tg.set(j + 1, 'O');
                    }
                    break;
                }
            }
        }
    }
}
