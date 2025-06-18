package com.mazemaster.services.generator;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Реализация алгоритма Прима для генерации лабиринтов.
 */
public class PrimMazeGenerator implements Generator {
    /**
     * Генератор случайных чисел для выбора начальной точки и направлений.
     */
    private final Random random = new Random();
    /**
     * Перечисление возможных направлений для создания проходов.
     */
    private enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
    /**
     * Генерирует лабиринт с использованием алгоритма Прима.
     *
     * @param width  ширина лабиринта
     * @param height высота лабиринта
     * @return сгенерированный лабиринт
     * @throws IllegalArgumentException если ширина или высота меньше или равна нулю
     */
    @Override
    public Maze generate(int width, int height) {
        // Проверка на валидность размеров: обе размерности должны быть > 0
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    String.format("Неверные размеры лабиринта: width=%d, height=%d", width, height)
            );
        }

        Maze maze = new Maze(width, height);
        Cell[][] grid = maze.grid();

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
            int x = currentCell.getCoordinate().x();
            int y = currentCell.getCoordinate().y();

            if (currentCell.isWall()) {
                currentCell.setWall(false);
                frontiers.remove(idx);

                createPassageInDirection(x, y, maze);
                getFrontiers(x, y, frontiers, maze);
            }
        }

        return maze;
    }
    /**
     * Собирает пограничные клетки для текущей позиции.
     *
     * @param x         x-координата текущей клетки
     * @param y         y-координата текущей клетки
     * @param frontiers список пограничных клеток
     * @param maze      лабиринт
     */
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
    /**
     * Создает проход в случайном направлении от текущей клетки к соседней непосещенной клетке.
     *
     * @param x    x-координата текущей клетки
     * @param y    y-координата текущей клетки
     * @param maze лабиринт
     */
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
    /**
     * Проверяет, находятся ли координаты в пределах лабиринта.
     *
     * @param x    x-координата
     * @param y    y-координата
     * @param maze лабиринт
     * @return true, если координаты в пределах лабиринта, иначе false
     */
    private boolean isInBounds(int x, int y, Maze maze) {
        return x >= 0 && y >= 0 && x < maze.width() && y < maze.height();
    }
}

