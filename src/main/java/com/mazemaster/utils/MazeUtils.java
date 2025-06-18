package com.mazemaster.utils;

import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

/**
 * Утилитный класс для операций с лабиринтами, таких как проверка границ.
 */
public class MazeUtils {
    /**
     * Проверяет, находятся ли начальная и конечная координаты в пределах лабиринта.
     *
     * @param maze  лабиринт для проверки
     * @param start начальная координата
     * @param end   конечная координата
     * @throws IllegalArgumentException если начальная или конечная координата вне границ
     */
    public static void checkBounds(Maze maze, Coordinate start, Coordinate end) {
        int maxX = maze.width();
        int maxY = maze.height();

        if (start.x() < 0 || start.x() >= maxX || start.y() < 0 || start.y() >= maxY) {
            throw new IllegalArgumentException("Стартовая координата вне границ: " + start);
        }

        if (end.x() < 0 || end.x() >= maxX || end.y() < 0 || end.y() >= maxY) {
            throw new IllegalArgumentException("Конечная координата вне границ: " + end);
        }
    }
}