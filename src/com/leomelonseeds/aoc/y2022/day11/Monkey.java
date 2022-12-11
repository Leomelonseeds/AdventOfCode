package com.leomelonseeds.aoc.y2022.day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
    
    private long inspectionAmount;
    private int id;
    private List<Long> items;
    private String operation;
    private int divisibleBy;
    private int ifTrue;
    private int ifFalse;
    
    public Monkey(int id, List<String> data) {
        this.id = id;
        this.inspectionAmount = 0;
        items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String s = data.get(i);
            int colon = s.indexOf(':');
            String cur = s.substring(colon + 2);
            String a[] = cur.split(" ");
            switch (i) {
            case 0:
                for (String num : cur.split(", ")) {
                    items.add(Long.parseLong(num));
                }
                break;
            case 1:
                operation = cur.substring(6);
                break;
            case 2:
                divisibleBy = Integer.parseInt(a[2]);
                break;
            case 3:
                ifTrue = Integer.parseInt(a[3]);
                break;
            case 4:
                ifFalse = Integer.parseInt(a[3]);
            }
        }
    }
    
    public List<Long> getList() {
        return items;
    }

    public int getDivisible() {
        return divisibleBy;
    }
    
    public int getTrue() {
        return ifTrue;
    }
    
    public int getFalse() {
        return ifFalse;
    }
    
    public int getID() {
        return id;
    }
    
    public long getInspectionAmount() {
        return inspectionAmount;
    }
    
    public long performOperation(long i) {
        inspectionAmount++;
        
        String args[] = operation.split(" ");
        long i1 = args[0].equals("old") ? i : Long.parseLong(args[0]);
        long i2 = args[2].equals("old") ? i : Long.parseLong(args[2]);
        
        switch (args[1]) {
        case "+":
            return i1 + i2;
        case "*":
            return i1 * i2;
        }
        return 0;
    }
}
