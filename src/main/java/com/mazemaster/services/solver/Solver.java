package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.List;

/**
 * Интерфейс для алгоритмов решения лабиринтов.
 */
public interface Solver {
    /**
     * Решает лабиринт от начальной до конечной координаты с использованием реализующего алгоритма.
     *
     * @param maze  лабиринт для решения
     * @param start начальная координата
     * @param end   конечная координата
     * @return список клеток, представляющих путь от начальной до конечной точки, или null, если путь не найден
     */
    List<Cell> solve(Maze maze, Coordinate start, Coordinate end);
}

