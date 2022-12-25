package com.leomelonseeds.aoc.y2022.day24;

import java.util.List;

public class Blizzard {
    
    private char dir;
    private List<Integer> pos;
    
    public Blizzard(char dir, List<Integer> pos) {
        this.dir = dir;
        this.pos = pos;
    }

    public char getDir() {
        return dir;
    }
    
    public List<Integer> getPos() {
        return pos;
    }
    
    public void move() {
        switch (dir) {
        case '^':
            int y_new = pos.get(1) - 1;
            if (y_new == 0) {
                y_new = Day24_1.MAX_Y - 1;
            }
            pos.set(1, y_new);
            break;
        case 'v':
            int y_new2 = pos.get(1) + 1;
            if (y_new2 == Day24_1.MAX_Y) {
                y_new2 = 1;
            }
            pos.set(1, y_new2);
            break;
        case '>':
            int x_new = pos.get(0) + 1;
            if (x_new == Day24_1.MAX_X) {
                x_new = 1;
            }
            pos.set(0, x_new);
            break;
        case '<':
            int x_new2 = pos.get(0) - 1;
            if (x_new2 == 0) {
                x_new2 = Day24_1.MAX_X - 1;
            }
            pos.set(0, x_new2);
        }
    }
}
