package com.leomelonseeds.aoc.y2023.day3;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day3_2 {

    public static void main(String[] args) {
        int sum = 0;
        List<String> input = Utils.getInput();
        List<Gear> gears = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            int indexOfLastNum = -1;
            int lenLastNum = -1;
            String s = input.get(i);
            for (int j = 0; j < s.length(); j++) {
                boolean isDigit = Utils.isDigit(s.charAt(j));
                if (isDigit) {
                    if (indexOfLastNum == -1) {
                        indexOfLastNum = j;
                        lenLastNum = 1;
                    } else {
                        lenLastNum++;
                    }
                }
                
                if (indexOfLastNum != -1 && (!isDigit || j == s.length() - 1)) {
                    // Code to detect parts
                    for (int i1 = i - 1; i1 <= i + 1; i1++) {
                        for (int j1 = indexOfLastNum - 1; j1 <= indexOfLastNum + lenLastNum; j1++) {
                            if (i1 == -1 || i1 == input.size() || j1 == -1 || j1 == s.length()){
                                continue;
                            }
                            
                            if (i1 == i && (j1 >= indexOfLastNum && j1 < indexOfLastNum + lenLastNum)) {
                                continue;
                            }
                            
                            if (input.get(i1).charAt(j1) == '*') {
                                boolean isNew = true;
                                for (Gear g : new ArrayList<>(gears)) {
                                    if (g.isEqual(i1, j1)) {
                                        isNew = false;
                                        if (!g.addNum(Integer.parseInt(s.substring(indexOfLastNum, indexOfLastNum + lenLastNum)))) {
                                            gears.remove(g);
                                        }
                                    }
                                }
                                
                                if (isNew) {
                                    Gear g = new Gear(i1, j1);
                                    g.addNum(Integer.parseInt(s.substring(indexOfLastNum, indexOfLastNum + lenLastNum)));
                                    gears.add(g);
                                }
                            }
                        }
                    }
                    
                    indexOfLastNum = -1;
                    lenLastNum = -1;
                }
            }
        }
        
        for (Gear g : gears) {
            sum += g.getRatio();
        }
        
        Utils.println(sum);
    }

}
