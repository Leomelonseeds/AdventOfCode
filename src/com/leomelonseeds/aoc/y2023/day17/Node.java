package com.leomelonseeds.aoc.y2023.day17;

import java.util.Objects;

import com.leomelonseeds.aoc.Coord;

public class Node {
    
    private Coord c;
    private Coord ldir;
    private Node prev;
    
    public Node(Coord c) {
        this.c = c;
        ldir = null;
        prev = null;
    }
    
    public Node(Node n) {
        this.c = new Coord(n.c);
        ldir = n.ldir == null ? null : new Coord(n.ldir);
        prev = n.prev;
    }
    
    public Coord ldir() {
        return ldir;
    }
    
    public void ldir(Coord ldir) {
        this.ldir = ldir;
    }
    
    public void prev(Node p) {
        prev = p;
    }
    
    public Node prev() {
        return prev;
    }
    
    public Coord coord() {
        return c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, ldir);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        return Objects.equals(c, other.c) && Objects.equals(ldir, other.ldir);
    }
}
