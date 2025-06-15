package com.mazemaster.services.generator;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KruskalMazeGenerator implements Generator {
    private int[] parent;

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

        parent = new int[width * height];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            int x = i % width;
            int y = i / width;
            grid[y][x].setWall(true);
        }

        List<Edge> edges = new ArrayList<>();
        for (int y = 0; y < height; y += 2) {
            for (int x = 0; x < width; x += 2) {
                if (x + 2 < width) {
                    edges.add(new Edge(x, y, x + 2, y));
                }
                if (y + 2 < height) {
                    edges.add(new Edge(x, y, x, y + 2));
                }
            }
        }

        Collections.shuffle(edges, new Random());

        for (Edge edge : edges) {
            int cell1 = edge.y1 * width + edge.x1;
            int cell2 = edge.y2 * width + edge.x2;
            int set1 = find(cell1);
            int set2 = find(cell2);

            if (set1 != set2) {
                union(set1, set2);
                grid[edge.y1][edge.x1].setWall(false);
                grid[edge.y2][edge.x2].setWall(false);
                grid[(edge.y1 + edge.y2) / 2][(edge.x1 + edge.x2) / 2].setWall(false);
            }
        }

        return maze;
    }

    private int find(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            int result = find(parent[i]);
            parent[i] = result;
            return result;
        }
    }

    private void union(int a, int b) {
        parent[b] = a;
    }

    private class Edge {
        int x1;
        int y1;
        int x2;
        int y2;

        Edge(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}

