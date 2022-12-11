package com.leomelonseeds.aoc.y2022.day7;

public abstract class Composite {
    
    protected String name;
    
    public Composite(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract int getSize();
} 
