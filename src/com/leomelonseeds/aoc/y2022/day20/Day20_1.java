package com.leomelonseeds.aoc.y2022.day20;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day20_1 {

    public static void main(String[] args) {
        List<Integer> positions = new ArrayList<>();
        List<Integer> code = new ArrayList<>();
        List<String> input = Utils.getInput();
        int length = input.size();
        for (int i = 0; i < length; i++) {
            code.add(Integer.parseInt(input.get(i)));
            positions.add(i);
        }
        
        for (int i = 0; i < length; i++) {
            int index = positions.indexOf(i);
            positions.remove(index);
            int toMove = code.remove(index);
            int newPos = getIndex(toMove, length, index, true);
            code.add(newPos, toMove);
            positions.add(newPos, i);
        }
        
        int total = 0;
        int zeroindex = code.indexOf(0);
        for (int i : new int[] {1000, 2000, 3000}) {
            int cur = getIndex(i, length, zeroindex, false);
            total += code.get(cur);
        }
        
        Utils.println(total);
    }
    
    private static int getIndex(int toMove, int length, int index, boolean removed) {
        int r = removed ? 1 : 0;
        int amount = toMove % (length - r);
        if (index + amount >= length) {
            amount -= length - r;
        } else if (index + amount <= 0) {
            amount += length - r;
        }
        return index + amount;
    }
}
