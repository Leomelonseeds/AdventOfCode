package com.leomelonseeds.aoc.y2023.day16;

import java.util.Objects;

import com.leomelonseeds.aoc.Coord;

public class Laser {
    
    private Coord coord;
    private char direction; //nsew
    
    public Laser(Coord coord, char direction) {
        this.coord = coord;
        this.direction = direction;
    }
    
    public Laser(Laser l) {
        this.coord = new Coord(l.getCoord());
        this.direction = l.getDir();
    }
    
    public Coord getCoord() {
        return coord;
    }
    
    public void move() {
        switch (direction) {
        case 'n':
            coord.y(coord.y() - 1);
            break;
        case 's':
            coord.y(coord.y() + 1);
            break;
        case 'e':
            coord.x(coord.x() + 1);
            break;
        case 'w':
            coord.x(coord.x() - 1);
        }
    }
    
    public void setDir(char d) {
        direction = d;
    }
    
    public char getDir() {
        return direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coord, direction);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Laser other = (Laser) obj;
        return Objects.equals(coord, other.coord) && direction == other.direction;
    }
}
