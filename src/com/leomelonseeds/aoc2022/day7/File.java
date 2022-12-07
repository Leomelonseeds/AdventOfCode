package com.leomelonseeds.aoc2022.day7;

public class File extends Composite {
    
    private int size;
    
    public File(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
