package com.leomelonseeds.aoc.y2022.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day11_1 {

    public static void main(String[] args) throws NumberFormatException {
        List<Monkey> monkeys = new ArrayList<>();
        List<String> input = Utils.getInput();
        for (int i = 0; i <= 7; i++) {
            int curIndex = i * 7;
            List<String> currentStore = new ArrayList<>();
            for (int j = 1; j <= 5; j++) {
                currentStore.add(input.get(curIndex + j));
            }
            monkeys.add(new Monkey(i, currentStore));
        }
        
        for (int i = 0; i < 20; i++) {
            for (Monkey m : monkeys) {
                List<Long> worries = m.getList();
                final int size = worries.size();
                for (int j = 0; j < size; j++) {
                    long worry = worries.remove(0);
                    long newWorry = m.performOperation(worry) / 3;
                    int thrownID;
                    if (newWorry % m.getDivisible() == 0) {
                        thrownID = m.getTrue();
                    } else {
                        thrownID = m.getFalse();
                    }
                    for (Monkey m2 : monkeys) {
                        if (m2.getID() == thrownID) {
                            m2.getList().add(newWorry);
                            break;
                        }
                    }
                }
            }
        }
        
        List<Long> top = new ArrayList<>();
        for (Monkey m : monkeys) {
            top.add(m.getInspectionAmount());
        }
        Collections.sort(top);
        Collections.reverse(top);
        
        Utils.println(top.get(0) * top.get(1));
    }
}
