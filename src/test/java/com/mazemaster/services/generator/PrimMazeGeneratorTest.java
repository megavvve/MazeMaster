package com.mazemaster.services.generator;

import com.mazemaster.model.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimMazeGeneratorTest {

    @Test
    void testGenerateSmallMaze() {
        PrimMazeGenerator generator = new PrimMazeGenerator();
        Maze maze = generator.generate(5, 5);
        assertNotNull(maze);
        assertEquals(5, maze.getWidth());
        assertEquals(5, maze.getHeight());
    }

    @Test
    void testGenerateLargeMaze() {
        PrimMazeGenerator generator = new PrimMazeGenerator();
        Maze maze = generator.generate(50, 50);
        assertNotNull(maze);
        assertEquals(50, maze.getWidth());
        assertEquals(50, maze.getHeight());
    }

    @Test
    void testGenerateMazeWithInvalidDimensions() {
        PrimMazeGenerator generator = new PrimMazeGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(0, 5));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(5, 0));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-1, 5));
    }
}

