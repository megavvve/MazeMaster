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

/**
 * Реализация алгоритма поиска в ширину (BFS) для решения лабиринтов.
 */
public class BFSSolver implements Solver {
    /**
     * Решает лабиринт от начальной до конечной координаты с использованием BFS.
     *
     * @param maze  лабиринт для решения
     * @param start начальная координата
     * @param end   конечная координата
     * @return список клеток, представляющих кратчайший путь от начальной до конечной точки, или null, если путь не найден
     */
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
        return null;
    }

    /**
     * Восстанавливает путь от начальной до конечной клетки.
     *
     * @param cameFrom карта, где ключ — клетка, а значение — клетка, из которой она достигнута
     * @param startCell начальная клетка
     * @param endCell конечная клетка
     * @return список клеток, представляющих путь
     */
    private List<Cell> reconstructPath(Map<Cell, Cell> cameFrom, Cell startCell, Cell endCell) {
        List<Cell> path = new ArrayList<>();
        Cell current = endCell;
        while (current != null && !current.equals(startCell)) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }
        if (current != null && current.equals(startCell)) {
            path.addFirst(startCell);
        }
        return path;
    }

    private List<Cell> getNeighbors(Cell cell, Maze maze) {
        return MazeCellNeighborFinder.getNeighbors(cell, maze);
    }
}