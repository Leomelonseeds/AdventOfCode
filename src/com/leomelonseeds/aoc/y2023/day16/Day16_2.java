package com.leomelonseeds.aoc.y2023.day16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leomelonseeds.aoc.Coord;
import com.leomelonseeds.aoc.Utils;

public class Day16_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        char[][] grid = new char[input.get(0).length()][input.size()];
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            for (int j = 0; j < s.length(); j++) {
                grid[j][i] = s.charAt(j);
            }
        }
        
        int max = 0;

        for (int x = -1; x <= grid.length; x++) {
            for (int y = -1; y <= grid[0].length; y++) {
                if (x > -1 && x < grid.length && y > -1 && y < grid[0].length) {
                    continue;
                }
                
                char dir;
                if (x == -1) {
                    dir = 'e';
                } else if (x == grid.length) {
                    dir = 'w';
                } else if (y == -1) {
                    dir = 's';
                } else {
                    dir = 'n';
                }
                
                List<Laser> lasers = new ArrayList<>();
                Set<Laser> history = new HashSet<>();
                Set<Coord> coords = new HashSet<>();
                lasers.add(new Laser(new Coord(x, y), dir));
                while (!lasers.isEmpty()) {
                    Set<Laser> toRemove = new HashSet<>();
                    Set<Laser> toAdd = new HashSet<>();
                    for (Laser l : lasers) {
                        if (history.contains(l)) {
                            toRemove.add(new Laser(l));
                            continue;
                        }
                        history.add(new Laser(l));
                        
                        l.move();
                        Coord co = l.getCoord();
                        if (co.x() >= grid.length || co.x() < 0 ||
                            co.y() >= grid[0].length || co.y() < 0) {
                            toRemove.add(new Laser(l));
                            continue;
                        }

                        coords.add(new Coord(co));
                        char c = grid[co.x()][co.y()];
                        char d = l.getDir();
                        switch (c) {
                        case '|':
                            if (d == 'e' || d == 'w') {
                                l.setDir('n');
                                Laser nl = new Laser(new Coord(co), 's');
                                if (!history.contains(nl)) {
                                    toAdd.add(nl);
                                }
                            } 
                            break;
                        case '-':
                            if (d == 'n' || d == 's') {
                                l.setDir('e');
                                Laser nl = new Laser(new Coord(co), 'w');
                                if (!history.contains(nl)) {
                                    toAdd.add(nl);
                                }
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

                    lasers.addAll(toAdd);
                    lasers.removeAll(toRemove);
                }
                
                if (coords.size() > max) {
                    max = coords.size();
                }
            }
        }
        
        Utils.println(max);
    }
}
