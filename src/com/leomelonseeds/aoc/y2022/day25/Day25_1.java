package com.leomelonseeds.aoc.y2022.day25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day25_1 {

    public static void main(String[] args) {
        
        long sum = 0;
        for (String s : Utils.getInput()) {
            int len = s.length();
            long num = 0;
            for (int i = 0; i < len; i++) {
                char c =     s.charAt(i);
                int pow = len - i - 1;
                int tomultiply;
                switch (c) {
                case '-':
                    tomultiply = -1;
                    break;
                case '=':
                    tomultiply = -2;
                    break;
                default:
                    tomultiply = Character.getNumericValue(c);
                }
                num += Math.pow(5, pow) * tomultiply;
            }
            sum += num;
        }
        
        Utils.println(toSnafu(sum));
        Utils.println(toSnafu(1));
    }
    
    // Prints snafe numbers backwards btw
    private static String toSnafu(long i) {
        // Base 5 conversion
        List<Long> mods = new ArrayList<>();
        long q = i;
        while (q != 0) {
            mods.add(q % 5);
            q /= 5;
        }
        
        // Snafu conversion
        String result = "";
        for (int j = 0; j < mods.size(); j++) {
            long num = mods.get(j);
            if (num >= 3) {
                if (j == mods.size() - 1) {
                    mods.add(1L);
                } else {
                    mods.set(j + 1, mods.get(j + 1) + 1);
                }
                if (num == 5) {
                    mods.set(j, 0L);
                }
            }
        }
        
        // Format + print number
        Collections.reverse(mods);
        for (long l : mods) {
            result += l + "";
        }
        result = result.replace('4', '-');
        result = result.replace('3', '=');
        return result;
    }
}
