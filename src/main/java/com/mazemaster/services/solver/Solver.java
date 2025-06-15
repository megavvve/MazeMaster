package com.mazemaster.services.solver;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, Coordinate start, Coordinate end);
}

