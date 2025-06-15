package com.mazemaster.services.generator;

import com.mazemaster.model.Maze;

public interface Generator {
    public abstract Maze generate(int height, int width);
}
