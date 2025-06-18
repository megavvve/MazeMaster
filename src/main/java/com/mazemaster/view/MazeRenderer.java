package com.mazemaster.view;


import com.mazemaster.model.Cell;
import com.mazemaster.model.Maze;

import java.util.List;

import static com.mazemaster.utils.ConsoleColors.*;

public class MazeRenderer implements Renderer {
    public String render(Maze maze) {
        return render(maze, null);
    }

    public String render(Maze maze, List<Cell> path) {
        return render(maze, path, true);
    }

    public String render(Maze maze, List<Cell> path, boolean showCoordinates) {
        StringBuilder sb = new StringBuilder();
        int height = maze.height();
        int width = maze.width();
        Cell[][] grid = maze.grid();
        int maxRowDigits = showCoordinates ? String.valueOf(height).length() : 0;
        int maxColDigits = showCoordinates ? String.valueOf(width - 1).length() : 0;

        if (showCoordinates) {

            sb.append(" ".repeat(maxRowDigits + 1));
            for (int x = 0; x < width; x++) {
                if (x > 0) sb.append(" ");
                sb.append(COORD).append(String.format("%" + maxColDigits + "d", x+1)).append(RESET);
            }
            sb.append("\n");

        }

        for (int y = 0; y < height; y++) {
            if (showCoordinates) {
                sb.append(COORD).append(String.format("%" + maxRowDigits + "d ", y + 1)).append(RESET);
            }
            for (int x = 0; x < width; x++) {
                Cell cell = grid[y][x];
                String c;
                if (cell.isWall()) {
                    c = WALL + "#" + RESET;
                } else if (path != null && path.contains(cell)) {
                    c = PATH + "*" + RESET;
                } else {
                    c =  " " + RESET;
                }
                sb.append(c);
                if (showCoordinates && x < width - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}


