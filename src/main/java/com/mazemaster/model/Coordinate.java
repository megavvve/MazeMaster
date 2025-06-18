package com.mazemaster.model;

public record Coordinate(int x, int y) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Coordinate(int x1, int y1)) {
            return x == x1 && y == y1;
        }
        return false;
    }

}
