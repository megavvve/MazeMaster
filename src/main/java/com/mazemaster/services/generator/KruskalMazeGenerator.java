package com.mazemaster.services.generator;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Реализация алгоритма Краскала для генерации лабиринтов.
 */
public class KruskalMazeGenerator implements Generator {
    /**
     * Массив для отслеживания принадлежности клеток к множествам в алгоритме Краскала.
     */
    private int[] parent;

    /**
     * Генерирует лабиринт с использованием алгоритма Краскала.
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

        parent = new int[width * height];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            int x = i % width;
            int y = i / width;
            grid[y][x].setWall(true);
        }

        List<CoordinatePair> edges = new ArrayList<>();
        for (int y = 0; y < height; y += 2) {
            for (int x = 0; x < width; x += 2) {
                if (x + 2 < width) {
                    edges.add(new CoordinatePair(new Coordinate(x, y), new Coordinate(x + 2, y)));
                }
                if (y + 2 < height) {
                    edges.add(new CoordinatePair(new Coordinate(x, y), new Coordinate(x, y + 2)));
                }
            }
        }

        Collections.shuffle(edges, new Random());

        for (CoordinatePair edge : edges) {
            int cell1 = edge.start().y() * width + edge.start().x();
            int cell2 = edge.end().y() * width + edge.end().x();
            int set1 = find(cell1);
            int set2 = find(cell2);

            if (set1 != set2) {
                union(set1, set2);
                grid[edge.start().y()][edge.start().x()].setWall(false);
                grid[edge.end().y()][edge.end().x()].setWall(false);
                grid[(edge.start().y() + edge.end().y()) / 2][(edge.start().x() + edge.end().x()) / 2].setWall(false);
            }
        }

        return maze;
    }
    /**
     * Находит корень множества, к которому принадлежит элемент.
     *
     * @param i индекс элемента
     * @return корень множества
     */
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            int result = find(parent[i]);
            parent[i] = result;
            return result;
        }
    }
    /**
     * Объединяет два множества.
     *
     * @param a корень первого множества
     * @param b корень второго множества
     */
    private void union(int a, int b) {
        parent[b] = a;
    }
    /**
     * Внутренняя структура для представления пары координат, соединяющих клетки.
     *
     * @param start начальная координата
     * @param end   конечная координата
     */
    private record CoordinatePair(Coordinate start, Coordinate end) {
    }
}