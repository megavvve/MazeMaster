package com.mazemaster.services.solver;

import com.mazemaster.model.Coordinate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class PathReconstruct {
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