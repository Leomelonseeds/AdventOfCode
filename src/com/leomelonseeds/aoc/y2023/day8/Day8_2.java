package com.leomelonseeds.aoc.y2023.day8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leomelonseeds.aoc.Utils;

public class Day8_2 {

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
        
        List<Integer> steps = new ArrayList<>();
        int i = 0;
        for (String curNode : nodes.keySet()) {
            if (curNode.charAt(2) != 'A') {
                continue;
            }
            
            steps.add(0);
            while (curNode.charAt(2) != 'Z') {
                char c = instr.charAt(steps.get(i) % instr.length());
                if (c == 'L') {
                    curNode = nodes.get(curNode).l().name();
                } else {
                    curNode = nodes.get(curNode).r().name();
                }
                steps.set(i, steps.get(i) + 1);
            }
            
            i++;
        }
        
        BigInteger result = new BigInteger(steps.get(0) + "");
        for (int j = 1; j < steps.size(); j++) {
            int cur = steps.get(j);
            BigInteger a = new BigInteger(result + "");
            BigInteger b = new BigInteger(cur + "");
            result = a.multiply(b).divide(a.gcd(b));
        }
        
        Utils.println(result);
    }
}
