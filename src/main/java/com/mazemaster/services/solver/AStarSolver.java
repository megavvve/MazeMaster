package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

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

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Map<Cell, Integer> gScore = new HashMap<>();
        Set<Cell> closedSet = new HashSet<>();

        Cell startCell = maze.getCell(start.getX(), start.getY());
        Cell endCell = maze.getCell(end.getX(), end.getY());

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
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
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

    private class Node {
        Cell cell;
        int f;

        Node(Cell cell, int f) {
            this.cell = cell;
            this.f = f;
        }
    }
}

