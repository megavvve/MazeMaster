package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        int maxX = maze.getWidth();
        int maxY = maze.getHeight();

        // Проверяем, находится ли стартовая координата в пределах лабиринта
        if (start.getX() < 0 || start.getX() >= maxX ||
                start.getY() < 0 || start.getY() >= maxY) {
            throw new IllegalArgumentException("Стартовая координата вне границ: " + start);
        }

        // Проверяем, находится ли конечная координата в пределах лабиринта
        if (end.getX() < 0   || end.getX() >= maxX ||
                end.getY() < 0   || end.getY() >= maxY) {
            throw new IllegalArgumentException("Конечная координата вне границ: " + end);
        }

        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        Cell startCell = maze.getCell(start.getX(), start.getY());
        Cell endCell = maze.getCell(end.getX(), end.getY());

        queue.add(startCell);
        visited.add(startCell);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.equals(endCell)) {
                return reconstructPath(cameFrom, startCell, endCell);
            }

            for (Cell neighbor : getNeighbors(current, maze)) {
                if (!visited.contains(neighbor) && !neighbor.isWall()) {
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return null; // No path found
    }

    private List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell startCell, Cell endCell) {
        List<Cell> path = new ArrayList<>();
        Cell current = endCell;
        while (current != null && !current.equals(startCell)) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        if (current != null && current.equals(startCell)) {
            path.add(0, startCell);
        }
        return path;
    }

    private List<Cell> getNeighbors(Cell cell, Maze maze) {
        return MazeCellNeighborFinder.getNeighbors(cell, maze);
    }
}

