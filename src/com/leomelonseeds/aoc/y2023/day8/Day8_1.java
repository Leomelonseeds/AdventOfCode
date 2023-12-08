package com.leomelonseeds.aoc.y2023.day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leomelonseeds.aoc.Utils;

public class Day8_1 {

    public static void main(String[] args) {
        List<String> input = Utils.getInput();
        Map<String, Node> nodes = new HashMap<>();
        String instr = input.get(0);
        for (int i = 2; i < input.size(); i++) {
            String n = input.get(i).split(" = ")[0];
            nodes.put(n, new Node(n));
        }
        
        for (int i = 2; i < input.size(); i++) {
            String[] n = input.get(i).split(" = ");
            String[] lr = n[1].split(", ");
            String l = lr[0].substring(1);
            String r = lr[1].substring(0, 3);
            nodes.get(n[0]).setL(nodes.get(l));
            nodes.get(n[0]).setR(nodes.get(r));
        }
        
        String curNode = "AAA";
        int steps = 0;
        while (!curNode.equals("ZZZ")) {
            char c = instr.charAt(steps % instr.length());
            if (c == 'L') {
                curNode = nodes.get(curNode).l().name();
            } else {
                curNode = nodes.get(curNode).r().name();
            }
            steps++;
        }
        
        Utils.println(steps);
    }

}
