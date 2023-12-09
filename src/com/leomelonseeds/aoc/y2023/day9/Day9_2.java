package com.leomelonseeds.aoc.y2023.day9;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day9_2 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        int sum = 0;
        for  (String line : input) {
            List<List<Integer>> seqs = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            for (String s : line.split(" ")) {
                nums.add(Integer.parseInt(s));
            }
            seqs.add(nums);
            
            List<Integer> curseq = nums;
            while (true) {
                List<Integer> nseq = new ArrayList<>();
                for (int i = 0; i < curseq.size() - 1; i++) {
                    nseq.add(curseq.get(i + 1) - curseq.get(i));
                }
                if (zeroed(nseq)) {
                    break;
                }
                curseq = nseq;
                seqs.add(nseq);
            }
            
            int res = 0;
            for (int i = seqs.size() - 1; i >= 0; i--) {
                List<Integer> cur = seqs.get(i);
                res = cur.get(0) - res;
                if (i == 0) {
                    sum += res;
                }
            }
        }
        
        Utils.println(sum);
    }
    
    public static boolean zeroed(List<Integer> l) {
        for (int i : l) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
