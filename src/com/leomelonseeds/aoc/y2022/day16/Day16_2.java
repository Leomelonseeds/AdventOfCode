package com.leomelonseeds.aoc.y2022.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.leomelonseeds.aoc.Utils;

public class Day16_2 {
    
    private static List<Valve> valves;

    public static void main(String[] args) throws NumberFormatException {
        List<String> input = Utils.getInput();
        valves = new ArrayList<>();
        for (String s : input) {
            String a[] = s.split(" ");
            String name = a[1];
            int flow = Integer.parseInt(a[4].split("=")[1].replace(";", ""));
            List<String> leads = new ArrayList<>();
            for (int i = 9; i < a.length; i++) {
                leads.add(a[i].replace(",", ""));
            }
            valves.add(new Valve(name, flow, false, leads));
        }
        
        Map<List<List<Valve>>, Integer> queue = new HashMap<>();
        List<List<Valve>> init = new ArrayList<>();
        List<Valve> init_y = new ArrayList<>();
        List<Valve> init_e = new ArrayList<>();
        Valve aa = getByName("AA");
        init_y.add(aa);
        init_e.add(aa);
        init.add(init_y);
        init.add(init_e);
        queue.put(init, 0);
        bftravel(26, queue);
    }
    
    private static void bftravel(int minutes, Map<List<List<Valve>>, Integer> queue) {
        // Get max value from all values
        int max = 0;
        for (int i : queue.values()) {
            if (i > max) {
                max = i;
            }
        }
        
        // Finish if minutes done
        if (minutes == 1) {
            Utils.println(max);
            return;
        }

        // Prune entries that have do not have max number of entries + bias
        for (Entry<List<List<Valve>>, Integer> e : new HashMap<>(queue).entrySet()) {
            if (e.getValue() < max - 400) {
                queue.remove(e.getKey());
            }
        }
        
        minutes--;
        // Utils.println("Minutes left: " + minutes + ", max: " + max + ", scanning " + queue.size());
        for (List<List<Valve>> prev_both : new HashMap<>(queue).keySet()) {
            List<Valve> next_y = new ArrayList<>();
            List<Valve> next_e = new ArrayList<>();
            Set<Valve> combined = new HashSet<>(prev_both.get(0));
            combined.addAll(new HashSet<>(prev_both.get(1)));
            for (int i = 0; i < 2; i++) {
                List<Valve> prev = prev_both.get(i);
                Valve current = prev.get(prev.size() - 1);
                if (!current.isOpened() && current.getRate() > 0) {
                    current.open();
                    queue.put(prev_both, queue.get(prev_both) + minutes * current.getRate());
                    // Utils.println("Released " + minutes * current.getRate() + " from " + current.getName() + " remains " + minutes);
                    continue;
                }

                List<Valve> toAdd = i == 0 ? next_y : next_e;
                for (String lead : current) {
                    Valve newValve = null;
                    for (Valve v : combined) {
                        if (v.getName().equals(lead)) {
                            newValve = v;
                            break;
                        }
                    }
                    if (newValve == null) {
                        Valve v1 = getByName(lead);
                        toAdd.add(v1);
                        combined.add(v1);
                    } else {
                        toAdd.add(newValve);
                    }
                }
            }
            
            if (next_y.isEmpty() && next_e.isEmpty()) {
                continue;
            } 
            
            int last = queue.remove(prev_both);
            if (!next_y.isEmpty() && next_e.isEmpty()) {
                for (Valve y : next_y) {
                    List<List<Valve>> toPut = new ArrayList<>();
                    List<Valve> toAdd = new ArrayList<>(prev_both.get(0));
                    toAdd.add(y);
                    toPut.add(toAdd);
                    toPut.add(prev_both.get(1));
                    queue.put(toPut, last);
                }
            } else if (!next_e.isEmpty() && next_y.isEmpty()) {
                for (Valve e : next_e) {
                    List<List<Valve>> toPut = new ArrayList<>();
                    List<Valve> toAdd = new ArrayList<>(prev_both.get(1));
                    toAdd.add(e);
                    toPut.add(prev_both.get(0));
                    toPut.add(toAdd);
                    queue.put(toPut, last);
                }
            } else {
                List<List<Valve>> combinations = new ArrayList<>();
                for (Valve y : next_y) {
                    for (Valve e : next_e) {
                        if (e.equals(y)) {
                            continue;
                        }
                        if (combinations.stream().anyMatch(lv -> lv.contains(e) && lv.contains(y))) {
                            continue;
                        }
                        List<Valve> combo = new ArrayList<>();
                        combo.add(e);
                        combo.add(y);
                        combinations.add(combo);
                        List<List<Valve>> toPut = new ArrayList<>();
                        List<Valve> toAdd_y = new ArrayList<>(prev_both.get(0));
                        List<Valve> toAdd_e = new ArrayList<>(prev_both.get(1));
                        toAdd_y.add(y);
                        toAdd_e.add(e);
                        toPut.add(toAdd_y);
                        toPut.add(toAdd_e);
                        queue.put(toPut, last);
                    }
                }
            }
        }
        
        bftravel(minutes, queue);
    }
    
    private static Valve getByName(String valve) {
        Valve current = null;
        for (Valve v : valves) {
            if (v.getName().equals(valve)) {
                current = v;
                break;
            }
        }
        return current.clone();
    }
}
