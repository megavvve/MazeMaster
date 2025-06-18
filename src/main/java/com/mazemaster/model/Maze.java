package com.mazemaster.model;

/**
 * Запись, представляющая лабиринт с заданной шириной, высотой и сеткой клеток.
 */
public record Maze(int width, int height, Cell[][] grid) {

    /**
     * Создаёт новый лабиринт с заданными шириной и высотой.
     * Все клетки инициализируются с координатами и статусом стены по умолчанию.
     *
     * @param width  ширина лабиринта
     * @param height высота лабиринта
     */
    public Maze(int width, int height) {
        this(width, height, new Cell[width][height]);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid()[x][y] = new Cell(new Coordinate(x, y));
            }
        }
    }

    /**
     * Возвращает клетку лабиринта по заданным координатам.
     *
     * @param x x-координата
     * @param y y-координата
     * @return клетка по указанным координатам
     */
    public Cell getCell(int x, int y) {
        return grid()[x][y];
    }
}