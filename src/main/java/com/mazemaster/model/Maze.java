package com.mazemaster.model;

public record Maze(int width, int height, Cell[][] grid) {
    public Maze(int width, int height) {
        this(width, height, new Cell[width][height]);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid()[x][y] = new Cell(new Coordinate(x, y));
            }
        }
    }

    public Cell getCell(int x, int y) {
        return grid()[x][y];
    }
}