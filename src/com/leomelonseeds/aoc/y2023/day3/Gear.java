package com.leomelonseeds.aoc.y2023.day3;

public class Gear {
    
    private int i;
    private int j;
    private int num1;
    private int num2;
    
    public Gear(int i, int j) {
        this.i = i;
        this.j = j;
        num1 = -1;
        num2 = -1;
    }
    
    public boolean isEqual(int x, int y) {
        return (i == x && j == y);
    }
    
    public boolean addNum(int num) {
        if (num1 == -1) {
            num1 = num;
        } else if (num2 == -1) {
            num2 = num;
        } else {
            return false;
        }
        
        return true;
    }
    
    public int getRatio() {
        if (num1 * num2 < 0) {
            return 0;
        }
        return num1 * num2;
    }

}
