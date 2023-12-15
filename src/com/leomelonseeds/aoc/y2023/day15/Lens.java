package com.leomelonseeds.aoc.y2023.day15;

import java.util.Objects;

public class Lens {
    
    private String id;
    private int l;
    
    public Lens(String id, int l) {
        this.id = id;
        this.l = l;
    }
    
    public int length() {
        return l;
    }
    
    public void setlength(int l) {
        this.l = l;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Lens other = (Lens) obj;
        return Objects.equals(id, other.id);
    }
    
    @Override
    public String toString() {
        return id + " " + l;
    }
}
