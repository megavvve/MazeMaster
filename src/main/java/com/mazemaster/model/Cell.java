package com.mazemaster.model;

/**
 * Класс, представляющий клетку лабиринта с координатами и статусом стены.
 */
public class Cell {
    /**
     * Координата клетки в лабиринте.
     */
    private final Coordinate coordinate;

    /**
     * Флаг, указывающий, является ли клетка стеной.
     */
    private boolean isWall;

    /**
     * Создаёт новую клетку с заданной координатой.
     *
     * @param coordinate координата клетки
     */
    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.isWall = true;
    }

    /**
     * Возвращает координату клетки.
     *
     * @return координата клетки
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Проверяет, является ли клетка стеной.
     *
     * @return true, если клетка является стеной, иначе false
     */
    public boolean isWall() {
        return isWall;
    }

    /**
     * Устанавливает статус стены для клетки.
     *
     * @param wall true, если клетка должна быть стеной, иначе false
     */
    public void setWall(boolean wall) {
        isWall = wall;
    }

    /**
     * Возвращает строковое представление клетки.
     *
     * @return строка, содержащая координаты и статус стены
     */
    @Override
    public String toString() {
        return "Cell{" +
                "coordinate=" + coordinate +
                ", isWall=" + isWall +
                '}' + "\n";
    }
}