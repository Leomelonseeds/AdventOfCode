package com.leomelonseeds.aoc.y2023.day7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.leomelonseeds.aoc.Utils;

public class Day7_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        
        Comparator<String> bySize = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String o1 = a.substring(0, 5);
                String o2 = b.substring(0, 5);
                
                List<Integer> otype = new ArrayList<>(); // set to 1 - 7; 1 is five of a kind etc
                
                for (String o : new String[] {o1, o2}) {
                    Map<Character, Integer> amt = new HashMap<>();
                    for (int i = 0; i < o.length(); i++) {
                        char c = o.charAt(i);
                        if (amt.get(c) == null) {
                            amt.put(c, 1);
                        } else {
                            amt.put(c, amt.get(c) + 1);
                        }
                    }
                    
                    if (amt.keySet().contains('J')) {
                        int max = 0;
                        char cmax = 0;
                        for (Entry<Character, Integer> e : amt.entrySet()) {
                            if (e.getKey() == 'J') {
                                continue;
                            }
                            
                            if (e.getValue() > max) {
                                max = e.getValue();
                                cmax = e.getKey();
                            }
                        }
                        
                        int jamt = amt.get('J');
                        if (jamt <= 4) {
                           amt.put(cmax, amt.get(cmax) + jamt);
                           amt.remove('J');
                        }
                    }
                    
                    switch(amt.keySet().size()) {
                    case 1:
                        otype.add(1);
                        continue;
                    case 2:
                        if (amt.values().contains(4)) {
                            otype.add(2);
                        } else if (amt.values().contains(3)) {
                            otype.add(3);
                        }
                        continue;
                    case 3:
                        if (amt.values().contains(3)) {
                            otype.add(4);
                        } else if (amt.values().contains(2)) {
                            otype.add(5);
                        }
                        continue;
                    case 4:
                        otype.add(6);
                        continue;
                    case 5:
                        otype.add(7);
                        continue;
                    }
                }
                
                int o1type = otype.get(0);
                int o2type = otype.get(1);
                
                return o1type == o2type ? compareByDigit(o1, o2) : o2type - o1type;
            }
        };
        
        input.sort(bySize);
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += (i + 1) * Integer.parseInt(input.get(i).split(" ")[1]);
        }
        
        Utils.println(sum);
    }
    
    public static int compareByDigit(String o1, String o2) {
        List<Character> order = new ArrayList<>(List.of(
                new Character[] {'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J'}));
        
        for (int i = 0; i < o1.length(); i++) {
            int a = order.indexOf(o1.charAt(i));
            int b = order.indexOf(o2.charAt(i));
            if (a == b) {
                continue;
            }
            
            return b - a;
        }
        
        return 0;
    }
}
