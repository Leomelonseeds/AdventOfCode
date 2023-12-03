package com.leomelonseeds.aoc.y2023.day3;

import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day3_1 {

    public static void main(String[] args) {
        String symbols = "!@#$%^&*()-+/=";
        int sum = 0;
        List<String> input = Utils.getInput();
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
                    boolean success = false;
                    for (int i1 = i - 1; i1 <= i + 1; i1++) {
                        for (int j1 = indexOfLastNum - 1; j1 <= indexOfLastNum + lenLastNum; j1++) {
                            if (i1 == -1 || i1 == input.size() || j1 == -1 || j1 == s.length()){
                                continue;
                            }
                            
                            if (i1 == i && (j1 >= indexOfLastNum && j1 < indexOfLastNum + lenLastNum)) {
                                continue;
                            }
                            
                            char c1 = input.get(i1).charAt(j1);
                            for (int k = 0; k < symbols.length(); k++) {
                                if (symbols.charAt(k) == c1) {
                                    success = true;
                                    break;
                                }
                            }
                            
                            if (success) {
                                break;
                            }
                        }
                        
                        if (success) {
                            break;
                        }
                    }
                    
                    if (success) {
                        sum += Integer.parseInt(s.substring(indexOfLastNum, indexOfLastNum + lenLastNum));
                        Utils.println(s.substring(indexOfLastNum, indexOfLastNum + lenLastNum));
                    }
                    
                    indexOfLastNum = -1;
                    lenLastNum = -1;
                }
            }
        }
        
        Utils.println(sum);
    }

}
