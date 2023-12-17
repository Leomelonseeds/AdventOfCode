package com.leomelonseeds.aoc.y2023.day17;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.leomelonseeds.aoc.Coord;
import com.leomelonseeds.aoc.Utils;

public class Day17_1 {
    
    static int[][] printgrid;

    public static void main(String[] args) {
        List<String> input = Utils.getInput("test");
        Map<Coord, Node> nodes = new HashMap<>();
        Map<Node, Integer> ds = new HashMap<>();
        Queue<Node> ns = new PriorityQueue<>(Comparator.comparingInt(n -> ds.get(n)));
        int[][] grid = new int[input.get(0).length()][input.size()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[j][i] = Integer.parseInt(input.get(i).charAt(j) + "");
                Coord c = new Coord(j, i);
                Node n = new Node(c);
                nodes.put(c, n);
                if (i == 0 && j == 0) {
                    ds.put(n, 0);
                    ns.add(n);
                }
            }
        }
        
        printgrid = grid;
        
        // Djikstra
        int res = 0x7fffffff;
        Set<Node> visited = new HashSet<>();
        Node end = null;
        while (!ns.isEmpty()) {
            Node cur = ns.remove();
            if (!visited.add(cur)) {
                continue;
            }
            
            Utils.println(cur.coord() + ", " + cur.ldir() + ", " + (cur.prev() != null ? cur.prev().coord() : ""));
            for (Entry<Node, Integer> e : getNeighbors(cur, nodes, grid, visited, ds).entrySet()) {
                Node v = e.getKey();
                int dist = e.getValue();
                if (v.coord().equals(new Coord(grid.length - 1, grid[0].length - 1))) {
                    if (dist < res) {
                        res = dist;
                        end = v;
                    }
                }
                
                if (!ds.containsKey(v)) {
                    ds.put(v, 0x7fffffff);
                }
                
                if (dist < ds.get(v)) {
                    ds.put(v, dist);
                    v.prev(cur);
                    ns.add(v);
                }
            }
        }
        
        Node cn = end;
        Utils.println(res + " " + end.prev().coord() + " " + end.ldir());
        while (cn != null) {
            printgrid[cn.coord().x()][cn.coord().y()] = 0;
            cn = cn.prev();
        }
        
        for (int i = 0; i < printgrid[0].length; i++) {
            for (int j = 0; j < printgrid.length; j++) {
                Utils.print(printgrid[j][i]);
            }
            Utils.println();
        }
    }
    
    public static Map<Node, Integer> getNeighbors(Node n, Map<Coord, Node> ns, int[][] g, Set<Node> visited, Map<Node, Integer> ds) {
        Map<Node, Integer> res = new HashMap<>();
        Coord c = n.coord();
        List<Coord> nc = new ArrayList<>();
        if (n.ldir() == null) {
            nc.add(new Coord(1, 0));
            nc.add(new Coord(-1, 0)); 
            nc.add(new Coord(0, 1));
            nc.add(new Coord(0, -1)); 
        } else {
            if (n.ldir().y() == 0) {
                nc.add(new Coord(0, 1));
                nc.add(new Coord(0, -1)); 
            }
            
            if (n.ldir().x() == 0) {
                nc.add(new Coord(1, 0));
                nc.add(new Coord(-1, 0)); 
            }   
        }
        
        
        for (Coord co : nc) {
            int cd = ds.get(n);
            for (int i = 1; i <= 3; i++) {
                Coord tc = c.add(co.multiply(i));
                Node tfc = ns.get(tc);
                if (tfc == null) {
                    break;
                }

                Node fc = new Node(tfc);
                fc.ldir(co);
                if (visited.contains(fc)) {
                    continue;
                }
                
                cd += g[tc.x()][tc.y()];
                res.put(fc, cd);
            }
        }

        Utils.println(nc.size() + ", " + res.size());
        return res;
    }
}
