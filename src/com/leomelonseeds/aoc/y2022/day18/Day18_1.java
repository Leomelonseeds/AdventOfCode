package com.leomelonseeds.aoc.y2022.day18;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day18_1 {

    public static void main(String[] args) throws NumberFormatException {
        List<List<Integer>> cubes = new ArrayList<>();
        for (String s : Utils.getInput()) {
            String a[] = s.split(",");
            List<Integer> pos = new ArrayList<>();
            for (String num : a) {
                pos.add(Integer.parseInt(num));
            }
            cubes.add(pos);
        }

        int total = cubes.size() * 6;
        for (List<Integer> l : cubes) {
            for (List<Integer> l2 : cubes) {
                for (int i = 0; i < 3; i++) {
                    List<Integer> ltemp = new ArrayList<>(l);
                    List<Integer> l2temp = new ArrayList<>(l2);
                    int lint = ltemp.remove(i);
                    int l2int = l2temp.remove(i);
                    if (ltemp.equals(l2temp) && Math.abs(lint - l2int) == 1) {
                        total--;
                    }
                }
            }
        }
        
        Utils.println(total);
    }
}
