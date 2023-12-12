package com.leomelonseeds.aoc.y2023.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day12_2 {
    
    private static HashMap<Combo, Long> map = new HashMap<>();

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            String tf = s.split(" ")[0];
            String f = tf;
            for (int j = 0; j < 4; j++) {
                f += "?" + tf;
            }
            String td = s.split(" ")[1];
            String d = td;
            for (int j = 0; j < 4; j++) {
                d += "," + td;
            }
            
            List<Integer> sizes = new ArrayList<>();
            for (String size : d.split(",")) {
                sizes.add(Integer.parseInt(size));
            }
            
            sum += numCombs(sizes, trimDots(f));
        }
        
        Utils.println(sum);
    }
    
    public static long numCombs(List<Integer> d, String f) {
        Combo combo = new Combo(d, f);
        if (map.containsKey(combo)) {
            return map.get(combo);
        }
        
        if (d.isEmpty() || d.size() == 1 && d.get(0) == 0) {
            if (f.isBlank()) {
                map.put(combo, (long) 1);
                return 1;
            }
            
            for (int i = 0; i < f.length(); i++) {
                if (f.charAt(i) == '#') {
                    map.put(combo, (long) 0);
                    return 0;
                }
            }

            map.put(combo, (long) 0);
            return 1;
        }
        
        if (!d.isEmpty() && f.isBlank()) {
            map.put(combo, (long) 0);
            return 0;
        }
        
        int ci = d.get(0);
        if (ci < 0) {
            map.put(combo, (long) 0);
            return 0;
        }
        
        if (ci > f.length()) {
            map.put(combo, (long) 0);
            return 0;
        }

        List<Integer> nextd = new ArrayList<>(d);
        char c = f.charAt(0);
        if (ci == 0) {
            if (c == '?' || c == '.') {
                nextd.remove(0);
                long res = numCombs(nextd, trimDots(f.substring(1)));
                map.put(combo, res);
                return res;
            }
            
            if (c == '#') {
                map.put(combo, (long) 0);
                return 0;
            }
        }

        c = f.charAt(0);
        if (c == '#') {
            nextd.set(0, ci - 1);
            if (ci > 1) {
                if (f.length() == 1) {
                    map.put(combo, (long) 0);
                    return 0;
                }
                
                if (f.charAt(1) == '.') {
                    map.put(combo, (long) 0);
                    return 0;
                }
                long res = numCombs(nextd, '#' + f.substring(2));
                map.put(combo, res);
                return res;
            } else {
                // ci = 1
                long res = numCombs(nextd, f.substring(1));
                map.put(combo, res);
                return res;
            }
        }
        
        if (c == '.') {
            map.put(combo, (long) 0);
            return 0;
        }
        
        // c must be ?
        long res = numCombs(new ArrayList<>(d), trimDots(f.substring(1))) + 
                numCombs(new ArrayList<>(d), '#' + f.substring(1));
        map.put(combo, res);
        return res;
    }
    
    public static String trimDots(String s) {
        s = s.replaceFirst("\\.++$", "");
        s = s.replaceFirst("^\\.+", "");
        s = s.replaceAll("\\.+", ".");
        return s;
    }
    
    // Thanks stackoverflow for the choose function
    public static long choose(long total, long choose){
        if(total < choose)
            return 0;
        if(choose <= 0 || choose == total)
            return 1;
        return choose(total-1,choose-1)+choose(total-1,choose);
    }
}
