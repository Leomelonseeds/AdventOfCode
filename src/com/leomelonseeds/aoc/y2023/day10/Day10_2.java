package com.leomelonseeds.aoc.y2023.day10;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day10_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        char[][] grid = new char[input.get(0).length()][input.size()];
        boolean[][] b = new boolean[input.get(0).length()][input.size()];
        int x = 0;
        int y = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                grid[j][i] = line.charAt(j);
                b[j][i] = false;
                if (grid[j][i] == 'S') {
                    x = j;
                    y = i;
                    b[j][i] = true;
                }
            }
        }
        
        int cx = x;
        int cy = y + 1; // First part of loop is | going down
        char ld = 's';
        
        while (!(cx == x && cy == y)) {
            b[cx][cy] = true;
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
        
        grid[x][y] = '|'; // REPLACE WITH LETTER IT SHOULD BE
        
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            boolean in = false;
            char lastturn = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (!b[i][j]) {
                    sum = in ? ++sum : sum;
                    continue;
                }
                
                char c = grid[i][j];
                switch (c) {
                case '|':
                    continue;
                case '-':
                    in = !in;
                    continue;
                case 'J':
                    if (lastturn == 'F') {
                        in = !in;
                    }
                    continue;
                case 'L':
                    if (lastturn == '7') {
                        in = !in;
                    }
                    continue;
                case 'F':
                case '7':
                    lastturn = c;
                    continue;
                }
            }
        }
        
        Utils.println(sum);
    }
}
