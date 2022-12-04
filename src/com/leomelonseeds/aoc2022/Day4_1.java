package com.leomelonseeds.aoc2022;

public class Day4_1 {

    public static void main(String[] args) throws NumberFormatException {
        int count = 0;
        for (String s : Utils.getInput()) {
            String pair[] = s.split(",");
            String nums1[] = pair[0].split("-");
            String nums2[] = pair[1].split("-");
            int a1 = Integer.parseInt(nums1[0]);
            int a2 = Integer.parseInt(nums1[1]);
            int b1 = Integer.parseInt(nums2[0]);
            int b2 = Integer.parseInt(nums2[1]);
            if ((a1 >= b1 && a2 <= b2) || (b1 >= a1 && b2 <= a2)) {
                count++;
            }
        }
        Utils.println(count);
    }
}
