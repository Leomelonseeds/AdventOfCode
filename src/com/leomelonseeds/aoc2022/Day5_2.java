package com.leomelonseeds.aoc2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5_2 {

    public static void main(String[] args) {
        Map<Integer, List<String>> lists = new HashMap<>();
        lists.put(1, new ArrayList<>(Arrays.asList(new String[] {"R", "S", "L", "F", "Q"})));
        lists.put(2, new ArrayList<>(Arrays.asList(new String[] {"N", "Z", "Q", "G", "P", "T"})));
        lists.put(3, new ArrayList<>(Arrays.asList(new String[] {"S", "M", "Q", "B"})));
        lists.put(4, new ArrayList<>(Arrays.asList(new String[] {"T", "G", "Z", "J", "H", "C", "B", "Q"})));
        lists.put(5, new ArrayList<>(Arrays.asList(new String[] {"P", "H", "M", "B", "N", "F", "S"})));
        lists.put(6, new ArrayList<>(Arrays.asList(new String[] {"P", "C", "Q", "N", "S", "L", "V", "G"})));
        lists.put(7, new ArrayList<>(Arrays.asList(new String[] {"W", "C", "F"})));
        lists.put(8, new ArrayList<>(Arrays.asList(new String[] {"Q", "H", "G", "Z", "W", "V", "P", "M"})));
        lists.put(9, new ArrayList<>(Arrays.asList(new String[] {"G", "Z", "D", "L", "C", "N", "R"})));
        for (String s : Utils.getInput()) {
            String a[] = s.split(" ");
            int first = Integer.parseInt(a[1]);
            int second = Integer.parseInt(a[3]);
            int third = Integer.parseInt(a[5]);
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < first; i++) {
                temp.add(lists.get(second).remove(lists.get(second).size() - 1));
            }
            Collections.reverse(temp);
            lists.get(third).addAll(temp);
        }
        for (int i = 1; i <= 9; i++) {
            List<String> l = lists.get(i);
            Utils.print(l.get(l.size() - 1));
        }
    }

}
