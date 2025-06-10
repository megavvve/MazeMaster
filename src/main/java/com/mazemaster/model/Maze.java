package com.mazemaster.model;

public class Maze {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Maze(int width, int height, Cell[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
}
