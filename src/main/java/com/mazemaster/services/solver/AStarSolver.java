package com.mazemaster.services.solver;

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
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Map<Coordinate, Coordinate> cameFrom = new HashMap<>();
        Map<Coordinate, Integer> gScore = new HashMap<>();
        Set<Coordinate> closedSet = new HashSet<>();

        gScore.put(start, 0);
        openSet.add(new Node(start, heuristic(start, end)));

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            Coordinate current = currentNode.coord;

            if (current.equals(end)) {
                return reconstructPath(cameFrom, start, end);
            }

            closedSet.add(current);

            for (Coordinate neighbor : getNeighbors(current, maze)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScore.getOrDefault(current, Integer.MAX_VALUE) + 1;

                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    int fScore = tentativeGScore + heuristic(neighbor, end);
                    openSet.add(new Node(neighbor, fScore));
                }
            }
        }
        return new ArrayList<>();
    }

    private int heuristic(Coordinate a, Coordinate b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom, Coordinate start, Coordinate end) {
        return PathReconstruct.reconstructPath(cameFrom, start, end);
    }

    private List<Coordinate> getNeighbors(Coordinate coord, Maze maze) {
        return MazeCellNeighborFinder.getNeighbors(coord, maze);
    }

    private class Node {
        Coordinate coord;
        int f;

        Node(Coordinate coord, int f) {
            this.coord = coord;
            this.f = f;
        }
    }
}