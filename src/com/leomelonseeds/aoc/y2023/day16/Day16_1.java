package com.leomelonseeds.aoc.y2023.day16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Coord;
import com.leomelonseeds.aoc.Utils;

public class Day16_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        List<Laser> lasers = new ArrayList<>();
        Set<Coord> coords = new HashSet<>();
        lasers.add(new Laser(new Coord(-1, 0), 'e'));
        char[][] grid = new char[input.get(0).length()][input.size()];
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            for (int j = 0; j < s.length(); j++) {
                grid[j][i] = s.charAt(j);
            }
        }
        
        int lastSum = 0;
        int timeslastsum = 0;
        while (timeslastsum < 100) {
            List<Laser> toRemove = new ArrayList<>();
            List<Laser> toAdd = new ArrayList<>();
            for (Laser l : lasers) {
                l.move();
                Coord co = l.getCoord();
                if (co.x() >= grid.length || co.x() < 0 ||
                    co.y() >= grid[0].length || co.y() < 0) {
                    toRemove.add(l);
                    continue;
                }
                coords.add(new Coord(co));
                char c = grid[co.x()][co.y()];
                char d = l.getDir();
                switch (c) {
                case '|':
                    if (d == 'e' || d == 'w') {
                        l.setDir('n');
                        addLaser(new Laser(new Coord(co), 's'), lasers, toAdd);
                        
                    } 
                    break;
                case '-':
                    if (d == 'n' || d == 's') {
                        l.setDir('e');
                        addLaser(new Laser(new Coord(co), 'w'), lasers, toAdd);
                    }
                    break;
                case '\\':
                    switch (d) {
                    case 'n':
                        l.setDir('w');
                        break;
                    case 'w':
                        l.setDir('n');
                        break;
                    case 's':
                        l.setDir('e');
                        break;
                    case 'e':
                        l.setDir('s');
                        break;
                    }
                    break;
                case '/':
                    switch (d) {
                    case 'n':
                        l.setDir('e');
                        break;
                    case 'e':
                        l.setDir('n');
                        break;
                    case 's':
                        l.setDir('w');
                        break;
                    case 'w':
                        l.setDir('s');
                        break;
                    }
                    break;
                }
            }
            
            lasers.removeAll(toRemove);
            lasers.addAll(toAdd);
            int sum = coords.size();
            if (sum == lastSum) {
                timeslastsum++;
            } else {
                timeslastsum = 0;
                lastSum = sum;
            }
        }
        
        Utils.println(lastSum);
    }
    
    public static void addLaser(Laser l, List<Laser> ls, List<Laser> toAdd) {
        if (!ls.contains(l) && !toAdd.contains(l)) {
            toAdd.add(l);
        }
    }
}
