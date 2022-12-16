package com.leomelonseeds.aoc.y2022.day16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Valve implements Iterable<String>, Cloneable {

    private String name;
    private int rate;
    private List<String> leads;
    private boolean opened;
    
    public Valve(String name, int rate, boolean opened, List<String> leads) {
        this.name = name;
        this.rate = rate;
        this.opened = opened;
        this.leads = leads;
    }

    public String getName() {
        return name;
    }
    
    public int getRate() {
        return rate;
    }
    
    public boolean isOpened() {
        return opened;
    }
    
    public void open() {
        opened = true;
    }

    @Override
    public Iterator<String> iterator() {
        return leads.iterator();
    }
    
    @Override
    public Valve clone() {
        return new Valve(name, rate, opened, new ArrayList<>(leads));
    }
}
