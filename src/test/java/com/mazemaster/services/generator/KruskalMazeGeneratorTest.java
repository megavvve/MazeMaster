package com.mazemaster.services.generator;

import com.mazemaster.model.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KruskalMazeGeneratorTest {

    @Test
    void testGenerateSmallMaze() {
        KruskalMazeGenerator generator = new KruskalMazeGenerator();
        Maze maze = generator.generate(5, 5);
        assertNotNull(maze);
        assertEquals(5, maze.width());
        assertEquals(5, maze.height());
    }

    @Test
    void testGenerateLargeMaze() {
        KruskalMazeGenerator generator = new KruskalMazeGenerator();
        Maze maze = generator.generate(50, 50);
        assertNotNull(maze);
        assertEquals(50, maze.width());
        assertEquals(50, maze.height());
    }

    @Test
    void testGenerateMazeWithInvalidDimensions() {
        KruskalMazeGenerator generator = new KruskalMazeGenerator();
        assertThrows(IllegalArgumentException.class, () -> generator.generate(0, 5));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(5, 0));
        assertThrows(IllegalArgumentException.class, () -> generator.generate(-1, 5));
    }
}

