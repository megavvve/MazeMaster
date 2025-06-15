package com.mazemaster.services.generator;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator implements Generator {
    private final Random random = new Random();

    private enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    @Override
    public Maze generate(int width, int height) {
        // Проверка на валидность размеров: обе размерности должны быть > 0
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    String.format("Неверные размеры лабиринта: width=%d, height=%d", width, height)
            );
        }

        Maze maze = new Maze(width, height);
        Cell[][] grid = maze.getGrid();

        Random rand = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x].setWall(true);
            }
        }

        int startX = rand.nextInt(width);
        int startY = rand.nextInt(height);
        grid[startY][startX].setWall(false);

        List<Cell> frontiers = new ArrayList<>();
        getFrontiers(startX, startY, frontiers, maze);

        while (!frontiers.isEmpty()) {
            int idx = rand.nextInt(frontiers.size());
            Cell currentCell = frontiers.get(idx);
            int x = currentCell.getCoordinate().getX();
            int y = currentCell.getCoordinate().getY();

            if (currentCell.isWall()) {
                currentCell.setWall(false);
                frontiers.remove(idx);

                createPassageInDirection(x, y, maze);
                getFrontiers(x, y, frontiers, maze);
            }
        }

        return maze;
    }

    private void getFrontiers(int x, int y, List<Cell> frontiers, Maze maze) {
        int shift = 2;
        int[][] dirs = {{0, -shift}, {0, shift}, {-shift, 0}, {shift, 0}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (isInBounds(nx, ny, maze) && maze.getCell(nx, ny).isWall() && !frontiers.contains(maze.getCell(nx, ny))) {
                frontiers.add(maze.getCell(nx, ny));
            }
        }
    }

    private void createPassageInDirection(int x, int y, Maze maze) {
        List<Direction> dirs = new ArrayList<>(List.of(Direction.values()));
        while (!dirs.isEmpty()) {
            int idx = random.nextInt(dirs.size());
            Direction direction = dirs.get(idx);
            dirs.remove(idx);
            switch (direction) {
                case NORTH:
                    if (isInBounds(x, y - 2, maze) && !maze.getCell(x, y - 2).isWall()) {
                        maze.getCell(x, y - 1).setWall(false);
                        dirs.clear();
                    }
                    break;
                case SOUTH:
                    if (isInBounds(x, y + 2, maze) && !maze.getCell(x, y + 2).isWall()) {
                        maze.getCell(x, y + 1).setWall(false);
                        dirs.clear();
                    }
                    break;
                case EAST:
                    if (isInBounds(x - 2, y, maze) && !maze.getCell(x - 2, y).isWall()) {
                        maze.getCell(x - 1, y).setWall(false);
                        dirs.clear();
                    }
                    break;
                case WEST:
                    if (isInBounds(x + 2, y, maze) && !maze.getCell(x + 2, y).isWall()) {
                        maze.getCell(x + 1, y).setWall(false);
                        dirs.clear();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isInBounds(int x, int y, Maze maze) {
        return x >= 0 && y >= 0 && x < maze.getWidth() && y < maze.getHeight();
    }
}

