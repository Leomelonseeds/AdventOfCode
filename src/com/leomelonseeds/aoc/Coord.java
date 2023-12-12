package com.leomelonseeds.aoc;

import java.util.Objects;

public class Coord {
    
    private int x;
    private int y;
    
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void x(int x) {
        this.x = x;
    }
    
    public void y(int y) {
        this.y = y;
    }
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coord other = (Coord) obj;
        return x == other.x && y == other.y;
    }
}
