package com.leomelonseeds.aoc.y2023.day12;

import java.util.List;
import java.util.Objects;

public class Combo {
    
    private List<Integer> d;
    private String f;
    
    public Combo(List<Integer> d, String f) {
        this.d = d;
        this.f = f;
    }

    @Override
    public int hashCode() {
        return Objects.hash(d, f);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Combo other = (Combo) obj;
        return Objects.equals(d, other.d) && Objects.equals(f, other.f);
    }

}
