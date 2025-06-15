package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.List;

public class MazeCellNeighborFinder {
    private MazeCellNeighborFinder() {

    }

    public static List<Coordinate> getNeighbors(Coordinate coord, Maze maze) {
        List<Coordinate> neighbors = new ArrayList<>();
        int x = coord.getX();
        int y = coord.getY();
        int shift = 1;
        int[][] dirs = {{0, -shift}, {0, shift}, {-shift, 0}, {shift, 0}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < maze.getHeight() && ny < maze.getWidth()) {
                if (maze.getCell(nx, ny).getType() == Cell.Type.PASSAGE) {
                    neighbors.add(new Coordinate(nx, ny));
                }
            }
        }
        return neighbors;
    }
}