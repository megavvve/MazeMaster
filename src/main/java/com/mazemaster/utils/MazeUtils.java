package com.mazemaster.utils;

import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

public class MazeUtils {
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