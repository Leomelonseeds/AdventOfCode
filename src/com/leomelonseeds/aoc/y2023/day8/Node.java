package com.leomelonseeds.aoc.y2023.day8;

public class Node {
    
    private String name;
    private Node r;
    private Node l;
    
    public Node(String name) {
        this.name = name;
    }
    
    public void setR(Node r) {
        this.r = r;
    }
    
    public void setL(Node l) {
        this.l = l;
    }
    
    public String name() {
        return name;
    }
    
    public Node r() {
        return r;
    }
    
    public Node l() {
        return l;
    }
}
