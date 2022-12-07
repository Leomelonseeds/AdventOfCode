package com.leomelonseeds.aoc2022;

import java.util.ArrayList;
import java.util.List;

import com.leomelonseeds.aoc2022.day7.File;
import com.leomelonseeds.aoc2022.day7.Folder;

public class Day7_1 {

    public static void main(String[] args) throws NumberFormatException {
        List<Folder> allF = new ArrayList<>();
        Folder main = new Folder("/", null);
        Folder currentF = main;
        boolean isList = false;
        for (String s : Utils.getInput()) {
            String a[] = s.split(" ");
            if (a[0].equals("$")) {
                if (isList) {
                    isList = false;
                }
                if (a[1].equals("ls")) {
                    isList = true;
                    continue;
                } else {
                    // must be a cd command
                    if (a[2].equals("..")) {
                        currentF = currentF.getHigher();
                    } else {
                        currentF = currentF.getByName(a[2]);
                    }
                }
            }
            
            if (isList) {
                if (a[0].equals("dir")) {
                    Folder f = new Folder(a[1], currentF);
                    currentF.addContent(f);
                    allF.add(f);
                } else {
                    currentF.addContent(new File(a[1], Integer.parseInt(a[0])));
                }
            }
        }
        
        int result = 0;
        for (Folder f : allF) {
            int size = f.getSize();
            if (size <= 100000) {
                result += size;
            }
        }
        Utils.print(result);
    }

}
