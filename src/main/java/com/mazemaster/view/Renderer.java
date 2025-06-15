package com.mazemaster.view;

import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;

import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}