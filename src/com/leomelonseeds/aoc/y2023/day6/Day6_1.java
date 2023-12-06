package com.leomelonseeds.aoc.y2023.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leomelonseeds.aoc.Utils;

public class Day6_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        
        int sum = 1;
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher1 = pattern.matcher(input.get(0));
        List<Integer> times = new ArrayList<>();
        while(matcher1.find()) {
            times.add(Integer.parseInt(matcher1.group(0)));
        }

        List<Integer> distances = new ArrayList<>();
        Matcher matcher2 = pattern.matcher(input.get(1));
        while(matcher2.find()) {
            distances.add(Integer.parseInt(matcher2.group(0)));
        }
        
        for (int i = 0; i < times.size(); i++) {
            int t = times.get(i);
            int d = distances.get(i);
            double discrim = Math.sqrt(t*t - 4*d);
            double lower = (t - discrim) / 2;
            double upper = (t + discrim) / 2;
            
            // i hate this omg
            if ((int) upper == upper) {
                upper -= 0.5;
            }
            
            if ((int) lower == lower) {
                lower += 0.5;
            }
            
            sum *= Math.floor(upper) - Math.ceil(lower) + 1;
        }
        
        Utils.println(sum);
    }

}
