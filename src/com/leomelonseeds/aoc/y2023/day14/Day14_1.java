package com.leomelonseeds.aoc.y2023.day14;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day14_1 {

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
        
        int sum = 0;
        List<List<Character>> fg = Utils.verticalList(grid);
        for (List<Character> tg : fg) {
            for (int i = 0; i < tg.size(); i++) {
                char cur = tg.get(i);
                if (cur != 'O') {
                    continue;
                }
                
                int csum = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (tg.get(j) == '.') {
                        if (j == 0) {
                            tg.set(j, 'O');
                            tg.set(i, '.');
                            csum += tg.size() - j;
                            break;
                        } else {
                            continue;
                        }
                    }
                    
                    if (tg.get(j + 1) == '.') {
                        tg.set(i, '.');
                        tg.set(j + 1, 'O');
                        csum += tg.size() - (j + 1);
                    }
                    break;
                }
                
                sum += csum == 0 ? tg.size() - i : csum;
            }
        }
        
        Utils.println(sum);
    }
}
