package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.List;

/**
 * Утилитный класс для поиска соседних клеток в лабиринте.
 */
public class MazeCellNeighborFinder {
    /**
     * Возвращает список соседних клеток, которые не являются стенами.
     *
     * @param cell клетка, для которой ищутся соседи
     * @param maze лабиринт, содержащий клетку
     * @return список соседних клеток, которые не являются стенами
     */

    public static List<Cell> getNeighbors(Cell cell, Maze maze) {
        List<Cell> neighbors = new ArrayList<>();
        int x = cell.getCoordinate().x();
        int y = cell.getCoordinate().y();
        int shift = 1;
        int[][] dirs = {{0, -shift}, {0, shift}, {-shift, 0}, {shift, 0}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < maze.width() && ny < maze.height()) {
                if (!maze.getCell(nx, ny).isWall()) {
                    neighbors.add(maze.getCell(nx, ny));
                }
            }
        }
        return neighbors;
    }
}

