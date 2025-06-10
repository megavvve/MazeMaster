package com.mazemaster.generator;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator implements Generator {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final Random random = new Random();

    private enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    public PrimMazeGenerator(int height, int width) {
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
    }

    @Override
    public Maze generate(int height, int width) {
        Random rand = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, Cell.Type.WALL);
            }
        }

        int startX = rand.nextInt(height);
        int startY = rand.nextInt(width);
        grid[startX][startY] = new Cell(startX, startY, Cell.Type.PASSAGE);

        List<Cell> frontiers = new ArrayList<>();
        getFrontiers(startX, startY, frontiers);

        while (!frontiers.isEmpty()) {
            int idx = rand.nextInt(frontiers.size());
            Cell coord = frontiers.get(idx);
            int x = coord.getX();
            int y = coord.getY();

            if (grid[x][y].getType() == Cell.Type.WALL) {
                grid[x][y].setType(Cell.Type.PASSAGE);
                frontiers.remove(idx);

                createPassageInDirection(x, y);
                getFrontiers(x, y, frontiers);
            }
        }

        return new Maze(width, height, grid);
    }

    private void getFrontiers(int x, int y, List<Cell> frontiers) {
        int shift = 2;
        int[][] dirs = {{0, -shift}, {0, shift}, {-shift, 0}, {shift, 0}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (isInBounds(nx, ny) && grid[nx][ny].getType() == Cell.Type.WALL && !frontiers.contains(grid[nx][ny])) {
                frontiers.add(grid[nx][ny]);
            }
        }
    }

    private void createPassageInDirection(int x, int y) {
        List<Direction> dirs = new ArrayList<>(List.of(Direction.values()));
        while (!dirs.isEmpty()) {
            int idx = random.nextInt(dirs.size());
            Direction direction = dirs.get(idx);
            dirs.remove(idx);
            switch (direction) {
                case NORTH:
                    if (isInBounds(x, y - 2) && grid[x][y - 2].getType() != Cell.Type.WALL) {
                        grid[x][y - 1].setType(Cell.Type.PASSAGE);
                        dirs.clear();
                    }
                    break;
                case SOUTH:
                    if (isInBounds(x, y + 2) && grid[x][y + 2].getType() != Cell.Type.WALL) {
                        grid[x][y + 1].setType(Cell.Type.PASSAGE);
                        dirs.clear();
                    }
                    break;
                case EAST:
                    if (isInBounds(x - 2, y) && grid[x - 2][y].getType() != Cell.Type.WALL) {
                        grid[x - 1][y].setType(Cell.Type.PASSAGE);
                        dirs.clear();
                    }
                    break;
                case WEST:
                    if (isInBounds(x + 2, y) && grid[x + 2][y].getType() != Cell.Type.WALL) {
                        grid[x + 1][y].setType(Cell.Type.PASSAGE);
                        dirs.clear();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < height && y < width;
    }
}
