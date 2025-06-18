package com.mazemaster.model;

/**
 * Запись, представляющая двумерную координату с значениями x и y.
 */
public record Coordinate(int x, int y) {

    /**
     * Проверяет, равна ли эта координата другому объекту.
     *
     * @param obj объект для сравнения
     * @return true, если объект является координатой с такими же значениями x и y, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return x == other.x() && y == other.y();
    }
}