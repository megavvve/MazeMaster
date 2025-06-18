package com.mazemaster.view;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Maze;

import java.util.List;
/**
 * Интерфейс для отображения лабиринтов в консоли.
 */
public interface Renderer {
    /**
     * Отображает лабиринт без пути.
     *
     * @param maze лабиринт для отображения
     * @return строковое представление лабиринта
     */
    String render(Maze maze);

    /**
     * Отображает лабиринт с указанным путем.
     *
     * @param maze лабиринт для отображения
     * @param path список клеток, образующих путь, или null, если путь не отображается
     * @return строковое представление лабиринта с выделенным путем
     */
    String render(Maze maze, List<Cell> path);
}

