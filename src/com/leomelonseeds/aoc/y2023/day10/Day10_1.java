package com.leomelonseeds.aoc.y2023.day10;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day10_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        char[][] grid = new char[input.get(0).length()][input.size()];
        int x = 0;
        int y = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid[j][i] = line.charAt(j);
                if (grid[j][i] == 'S') {
                    x = j;
                    y = i;
                }
            }
        }
        
        int cx = x;
        int cy = y + 1; // First part of loop is | going down
        char ld = 's';
        int steps = 0;
        
        while (!(cx == x && cy == y)) {
            steps++;
            switch (grid[cx][cy]) {
            case '|':
                cy = ld == 'n' ? --cy : ++cy;
                continue;
            case '-':
                cx = ld == 'e' ? ++cx : --cx;
                continue;
            case 'L':
                if (ld == 's') {
                    cx++;
                    ld = 'e';
                } else {
                    cy--;
                    ld = 'n';
                }
                continue;
            case 'J':
                if (ld == 's') {
                    cx--;
                    ld = 'w';
                } else {
                    cy--;
                    ld = 'n';
                }
                continue;
            case '7':
                if (ld == 'e') {
                    cy++;
                    ld = 's';
                } else {
                    cx--;
                    ld = 'w';
                }
                continue;
            case 'F':
                if (ld == 'w') {
                    cy++;
                    ld = 's';
                } else {
                    cx++;
                    ld = 'e';
                }
                continue;
            case '.':
                cx = x;
                cy = y;
            }
        }
        
        
        
        Utils.println((steps + 1) / 2);
    }
}
