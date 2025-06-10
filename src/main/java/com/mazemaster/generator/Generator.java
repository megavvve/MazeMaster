package com.mazemaster.generator;

import com.mazemaster.model.Maze;

interface Generator {
    public abstract Maze generate(int height, int width);
}
