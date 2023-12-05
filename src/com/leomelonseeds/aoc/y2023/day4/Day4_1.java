package com.leomelonseeds.aoc.y2023.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leomelonseeds.aoc.Utils;

public class Day4_1 {

    public static void main(String[] args) {
        int sum = 0;
        for (String s : Utils.getInput()) {
            String[] sargs = s.split("\\|");
            String s1 = sargs[0].split(":")[1];
            String s2 = sargs[1];
            
            Pattern pattern = Pattern.compile("\\d+");
            
            Matcher matcher1 = pattern.matcher(s1);
            List<Integer> card = new ArrayList<>();
            while(matcher1.find()) {
                card.add(Integer.parseInt(matcher1.group(0)));
            }

            List<Integer> have = new ArrayList<>();
            Matcher matcher2 = pattern.matcher(s2);
            while(matcher2.find()) {
                have.add(Integer.parseInt(matcher2.group(0)));
            }
            
            int pt = 0;
            for (int i : have) {
                if (card.contains(i)) {
                    pt = pt == 0 ? 1 : pt * 2;
                }
            }
            
            sum += pt;
            
        }
        
        Utils.println(sum);
        
    }

}
