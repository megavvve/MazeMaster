package com.mazemaster.example;

import com.mazemaster.model.Cell;
import com.mazemaster.model.Coordinate;
import com.mazemaster.model.Maze;
import com.mazemaster.services.generator.Generator;
import com.mazemaster.services.generator.KruskalMazeGenerator;
import com.mazemaster.services.generator.PrimMazeGenerator;
import com.mazemaster.services.solver.AStarSolver;
import com.mazemaster.services.solver.BFSSolver;
import com.mazemaster.services.solver.Solver;
import com.mazemaster.view.MazeRenderer;

import java.util.List;
import java.util.Scanner;

public class MazeExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Java Maze Master Library Demo ===");
        System.out.println("Добро пожаловать в генератор лабиринтов!");

        System.out.print("\nВведите ширину лабиринта: ");
        int width = sc.nextInt();

        System.out.print("Введите высоту лабиринта: ");
        int height = sc.nextInt();

        System.out.println("\nВыберите алгоритм генерации:");
        System.out.println("1 - Алгоритм Прима (рекомендуется)");
        System.out.println("2 - Алгоритм Краскала");
        System.out.print("Ваш выбор: ");
        int genChoice = sc.nextInt();

        Generator generator = (genChoice == 2) ?
                new KruskalMazeGenerator() : new PrimMazeGenerator();

        System.out.println("\nВыберите алгоритм решения:");
        System.out.println("1 - Поиск в ширину (BFS)");
        System.out.println("2 - A* (рекомендуется)");
        System.out.print("Ваш выбор: ");
        int solverChoice = sc.nextInt();

        Solver solver = (solverChoice == 1) ?
                new BFSSolver() : new AStarSolver();

        System.out.println("\nГенерация лабиринта " + width + "x" + height + "...");
        Maze maze = generator.generate(width, height);

        MazeRenderer renderer = new MazeRenderer();
        System.out.println("\n" + renderer.render(maze));

        System.out.print("\nВведите координату строки (по X) начальной точки: ");
        int startRow = sc.nextInt();
        System.out.print("Введите координату столбца (по Y) начальной точки: ");
        int startCol = sc.nextInt();

        System.out.print("Введите координату строки (по X) конечной точки: ");
        int endRow = sc.nextInt();
        System.out.print("Введите координату столбца (по Y) конечной точки: ");
        int endCol = sc.nextInt();

        Coordinate start = new Coordinate(startRow - 1, startCol - 1);
        Coordinate end = new Coordinate(endRow - 1, endCol - 1);

        System.out.println("\nПоиск пути...");
        List<Cell> path = solver.solve(maze, start, end);

        if (path != null && !path.isEmpty()) {
            System.out.println("\nПуть найден (" + path.size() + " шагов):");
            System.out.println(renderer.render(maze, path ));
        } else {
            System.out.println("\nПуть не найден!");
        }

        sc.close();
    }
}

