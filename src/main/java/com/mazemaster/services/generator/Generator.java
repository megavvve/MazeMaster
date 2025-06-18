package com.mazemaster.services.generator;

import com.mazemaster.model.Maze;

public interface Generator {
    /**
     * Генерирует лабиринт с указанными шириной и высотой.
     *
     * @param width  ширина лабиринта
     * @param height высота лабиринта
     * @return сгенерированный лабиринт
     */
    public abstract Maze generate(int height, int width);
}
