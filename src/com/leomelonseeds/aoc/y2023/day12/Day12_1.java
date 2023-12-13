package com.leomelonseeds.aoc.y2023.day12;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc.Utils;

public class Day12_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput("test");
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            String f = s.split(" ")[0];
            List<Integer> sizes = new ArrayList<>();
            for (String size : s.split(" ")[1].split(",")) {
                sizes.add(Integer.parseInt(size));
            }
            
            int exist = 0;
            int quest = 0;
            for (int j = 0; j < f.length(); j++) {
                if (f.charAt(j) == '#') {
                    exist++;
                } else if (f.charAt(j) == '?') {
                    quest++;
                }
            }
            
            int left = sizes.stream().mapToInt(z -> z).sum() - exist;
            
            int[] p = new int[quest];
            for (int j = 0; j < quest; j++) {
                p[j] = j < quest - left ? 0 : 1;
            }
            
            long curSum = 0;
            do {
                int c = 0;
                String cf = f;
                for (int j = 0; j < f.length(); j++) {
                    if (f.charAt(j) != '?') {
                        continue;
                    }
                    
                    cf = cf.substring(0, j) + (p[c] == 0 ? "." : "#") + (j + 1 >= f.length() ? "" : cf.substring(j+1)); 
                    c++;
                }
                
                cf = cf.replaceFirst("\\.++$", "");
                cf = cf.replaceFirst("^\\.+", "");
                String[] v = cf.split("\\.+");
                
                if (v.length != sizes.size()) {
                    continue;
                }
                
                boolean succ = true;
                for (int j = 0; j < v.length; j++) {
                    if (v[j].length() != sizes.get(j)) {
                        succ = false;
                        break;
                    }
                }
                
                curSum = succ ? ++curSum : curSum;
            } while(findNextPermutation(p));
            
            Utils.println(curSum);
            sum += curSum;
        }
        
        Utils.println(sum);
    }
    
    // THANKS GEEKSFORGEEKS LMAO
    // THIS SECTION IS FOR findNextPermutation, which
    // Function to swap the data
    // present in the left and right indices
    public static int[] swap(int data[], int left, int right)
    {
 
        // Swap the data
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
 
        // Return the updated array
        return data;
    }
 
    // Function to reverse the sub-array
    // starting from left to the right
    // both inclusive
    public static int[] reverse(int data[], int left, int right)
    {
 
        // Reverse the sub-array
        while (left < right) {
            int temp = data[left];
            data[left++] = data[right];
            data[right--] = temp;
        }
 
        // Return the updated array
        return data;
    }
 
    // Function to find the next permutation
    // of the given integer array
    public static boolean findNextPermutation(int data[])
    {
 
        // If the given dataset is empty
        // or contains only one element
        // next_permutation is not possible
        if (data.length <= 1)
            return false;
 
        int last = data.length - 2;
 
        // find the longest non-increasing suffix
        // and find the pivot
        while (last >= 0) {
            if (data[last] < data[last + 1]) {
                break;
            }
            last--;
        }
 
        // If there is no increasing pair
        // there is no higher order permutation
        if (last < 0)
            return false;
 
        int nextGreater = data.length - 1;
 
        // Find the rightmost successor to the pivot
        for (int i = data.length - 1; i > last; i--) {
            if (data[i] > data[last]) {
                nextGreater = i;
                break;
            }
        }
 
        // Swap the successor and the pivot
        data = swap(data, nextGreater, last);
 
        // Reverse the suffix
        data = reverse(data, last + 1, data.length - 1);
 
        // Return true as the next_permutation is done
        return true;
    }
}
