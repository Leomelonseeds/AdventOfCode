package com.leomelonseeds.aoc2022.day7;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Composite {
    
    public Folder higher;
    public List<Composite> contents;
    
    public Folder(String name, Folder higher) {
        super(name);
        contents = new ArrayList<>();
        this.higher = higher;
    }
    
    public Folder getHigher() {
        return higher;
    }
    
    public void addContent(Composite c) {
        contents.add(c);
    }
    
    public Folder getByName(String s) {
        for (Composite c : contents) {
            if (c instanceof Folder && c.getName().equals(s)) {
                return (Folder) c;
            }
        }
        return null;
    }
    
    @Override
    public int getSize() {
        int total = 0;
        for (Composite c : contents) {
            total += c.getSize();
        }
        return total;
    }
}
