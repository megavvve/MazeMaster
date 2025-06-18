package com.mazemaster.services.solver;

import com.mazemaster.model.Maze;
import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AStarSolverTest {

    @Test
    void testSolveSimpleMaze() {
        Maze maze = new Maze(3, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                maze.getCell(i, j).setWall(false);
            }
        }
        maze.getCell(0, 0).setWall(false);
        maze.getCell(0, 1).setWall(false);
        maze.getCell(0, 2).setWall(false);
        maze.getCell(1, 2).setWall(false);
        maze.getCell(2, 2).setWall(false);

        AStarSolver solver = new AStarSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);
        List<Cell> path = solver.solve(maze, start, end);

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals(maze.getCell(start.x(), start.y()), path.get(0));
        assertEquals(maze.getCell(end.x(), end.y()), path.get(path.size() - 1));
    }

    @Test
    void testSolveMazeNoPath() {
        Maze maze = new Maze(3, 3);
        AStarSolver solver = new AStarSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 2);
        List<Cell> path = solver.solve(maze, start, end);

        assertNull(path);
    }

    @Test
    void testSolveMazeStartEqualsEnd() {
        Maze maze = new Maze(1, 1);
        maze.getCell(0, 0).setWall(false);
        AStarSolver solver = new AStarSolver();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 0);
        List<Cell> path = solver.solve(maze, start, end);

        assertNotNull(path);
        assertEquals(1, path.size());
        assertEquals(maze.getCell(start.x(), start.y()), path.get(0));
    }

    @Test
    void testSolveMazeInvalidCoordinates() {
        Maze maze = new Maze(5, 5);
        AStarSolver solver = new AStarSolver();
        final Coordinate invalidStart = new Coordinate(-1, 0);
        final Coordinate validEnd = new Coordinate(2, 2);
        assertThrows(IllegalArgumentException.class, () -> solver.solve(maze, invalidStart, validEnd));

        final Coordinate validStart = new Coordinate(0, 0);
        final Coordinate invalidEnd = new Coordinate(5, 5);
        assertThrows(IllegalArgumentException.class, () -> solver.solve(maze, validStart, invalidEnd));
    }
}

