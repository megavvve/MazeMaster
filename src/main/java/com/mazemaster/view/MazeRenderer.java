package com.mazemaster.view;


import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.List;

public class MazeRenderer implements Renderer {
    public String render(Maze maze) {
        return render(maze, null);
    }

    public String render(Maze maze, List<Cell> path) {
        return render(maze, path, true);
    }

    public String render(Maze maze, List<Cell> path, boolean showCoordinates) {
        StringBuilder sb = new StringBuilder();
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] grid = maze.getGrid();
        int maxRowDigits = showCoordinates ? String.valueOf(height).length() : 0;
        int maxColDigits = showCoordinates ? String.valueOf(width - 1).length() : 0;

        // ANSI-коды для цветов
        String RESET = "\033[0m";
        String WALL = "\033[34m"; // Синий для стен
        String PATH = "\033[32m"; // Зеленый для пути

        String COORD = "\033[33m"; // Желтый для координат

        // Если showCoordinates true, добавляем заголовок с метками столбцов (0, 1, 2, ...)
        if (showCoordinates) {

            // Пробелы для выравнивания с номерами строк
            for (int i = 0; i < maxRowDigits + 1; i++) {
                sb.append(" ");
            }
            // Метки столбцов
            for (int x = 0; x < width; x++) {
                if (x > 0) sb.append(" ");
                // Форматируем номер столбца с учетом maxColDigits
                sb.append(COORD).append(String.format("%" + maxColDigits + "d", x+1)).append(RESET);
            }
            sb.append("\n");

        }

        // Рисуем лабиринт
        for (int y = 0; y < height; y++) {
            if (showCoordinates) {
                // Номер строки с выравниванием
                sb.append(COORD).append(String.format("%" + maxRowDigits + "d ", y + 1)).append(RESET);
            }
            for (int x = 0; x < width; x++) {
                Cell cell = grid[y][x];
                String c;
                if (cell.isWall()) { // Изменено с getType() == Cell.Type.WALL
                    c = WALL + "#" + RESET;
                } else if (path != null && path.contains(cell)) {
                    c = PATH + "*" + RESET;
                } else {
                    c =  " " + RESET;
                }
                sb.append(c);
                // Пробел между клетками для выравнивания, если showCoordinates true
                if (showCoordinates && x < width - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}


