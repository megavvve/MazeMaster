package com.mazemaster.model;

public class Cell {
    private final Coordinate coordinate;
    private boolean isWall;

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.isWall = true;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinate=" + coordinate +
                ", isWall=" + isWall +
                '}' + "\n";
    }
}

