package com.mazemaster.utils;

/**
 * Утилитный класс, содержащий ANSI-коды цветов для вывода в консоль.
 */
public final class ConsoleColors {
    public static final String RESET = "\033[0m";
    public static final String WALL = "\033[34m"; // Синий для стен
    public static final String PATH = "\033[32m"; // Зеленый для пути
    public static final String COORD = "\033[33m"; // Желтый для координат
}