package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;
import com.mazemaster.utils.MazeUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        MazeUtils.checkBounds(maze, start, end);

        int maxX = maze.width();
        int maxY = maze.height();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Map<Cell, Integer> gScore = new HashMap<>();
        Set<Cell> closedSet = new HashSet<>();

        Cell startCell = maze.getCell(start.x(), start.y());
        Cell endCell = maze.getCell(end.x(), end.y());

        gScore.put(startCell, 0);
        openSet.add(new Node(startCell, heuristic(startCell.getCoordinate(), endCell.getCoordinate())));

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            Cell current = currentNode.cell;

            if (current.equals(endCell)) {
                return reconstructPath(cameFrom, startCell, endCell);
            }

            closedSet.add(current);

            for (Cell neighbor : getNeighbors(current, maze)) {
                if (closedSet.contains(neighbor) || neighbor.isWall()) {
                    continue;
                }

                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;

                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    int fScore = tentativeGScore + heuristic(neighbor.getCoordinate(), endCell.getCoordinate());
                    openSet.add(new Node(neighbor, fScore));
                }
            }
        }
        return null;
    }

    private int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
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

    private static class Node {
        Cell cell;
        int fScore;

        Node(Cell cell, int fScore) {
            this.cell = cell;
            this.fScore = fScore;
        }
    }
}