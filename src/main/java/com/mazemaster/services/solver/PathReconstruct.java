package com.mazemaster.services.solver;

import com.mazemaster.model.Coordinate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Утилитный класс для восстановления путей в алгоритмах решения лабиринтов с использованием координат.
 */
public class PathReconstruct {
    /**
     * Восстанавливает путь от начальной до конечной координаты с использованием карты cameFrom.
     *
     * @param cameFrom карта, где ключ — координата, а значение — координата, из которой она достигнута
     * @param start    начальная координата
     * @param end      конечная координата
     * @return список координат, представляющих путь от начальной до конечной точки
     * @throws NullPointerException если путь не существует (т.е. cameFrom не содержит отображение от end к start)
     */
    public static List<Coordinate> reconstructPath(Map<Coordinate, Coordinate> cameFrom,
                                                   Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }


}