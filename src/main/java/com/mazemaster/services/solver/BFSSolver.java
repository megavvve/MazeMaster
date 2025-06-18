package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;
import com.mazemaster.utils.MazeUtils;

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
        MazeUtils.checkBounds(maze, start, end);

        int maxX = maze.width();
        int maxY = maze.height();

        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        Cell startCell = maze.getCell(start.x(), start.y());
        Cell endCell = maze.getCell(end.x(), end.y());

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