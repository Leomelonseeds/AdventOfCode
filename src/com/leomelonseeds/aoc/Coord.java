package com.leomelonseeds.aoc;

import java.util.Objects;

public class Coord {
    
    private int x;
    private int y;
    
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coord(Coord c) {
        this.x = c.x();
        this.y = c.y();
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
    
    public Coord multiply(int m) {
        return new Coord(x * m, y * m);
    }
    
    public Coord add(Coord c) {
        return new Coord(x + c.x(), y + c.y());
    }
    
    public Coord subtract(Coord c) {
        return new Coord(x - c.x(), y - c.y());
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
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
