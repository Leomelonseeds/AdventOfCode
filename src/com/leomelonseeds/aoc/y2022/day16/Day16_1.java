package com.leomelonseeds.aoc.y2022.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.leomelonseeds.aoc.Utils;

public class Day16_1 {
    
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
        
        Map<List<Valve>, Integer> queue = new HashMap<>();
        List<Valve> init = new ArrayList<>();
        init.add(getByName("AA"));
        queue.put(init, 0);
        bftravel(30, queue);
    }
    
    private static void bftravel(int minutes, Map<List<Valve>, Integer> queue) {
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
        for (Entry<List<Valve>, Integer> e : new HashMap<>(queue).entrySet()) {
            if (e.getValue() < max * 0.75) {
                queue.remove(e.getKey());
            }
        }
        
        minutes--;
        for (List<Valve> prev : new HashMap<>(queue).keySet()) {
            Valve current = prev.get(prev.size() - 1);
            if (!current.isOpened() && current.getRate() > 0) {
                current.open();
                queue.put(prev, queue.get(prev) + minutes * current.getRate());
                continue;
            }
            
            int last = queue.remove(prev);
            for (String lead : current) {
                List<Valve> toAdd = new ArrayList<>(prev);
                Valve newValve = null;
                for (Valve v : toAdd) {
                    if (v.getName().equals(lead)) {
                        newValve = v;
                        break;
                    }
                }
                if (newValve == null) {
                    toAdd.add(getByName(lead));
                } else {
                    toAdd.add(newValve);
                }
                queue.put(toAdd, last);
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
