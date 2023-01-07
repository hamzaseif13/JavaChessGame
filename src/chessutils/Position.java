package chessutils;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;
    private final String coordinates;

    public Position(int x, int y) {
        String[] arr = {"a", "b", "c", "d", "e", "f", "g", "h"};
        this.x = x;
        this.y = y;
        this.coordinates = arr[x - 1] + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public static Position of(String text) {
        int x = text.charAt(0) - 'a' + 1;
        int y = Integer.parseInt(text.substring(1, 2));
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && Objects.equals(coordinates, position.coordinates);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", coordinates='" + coordinates + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, coordinates);
    }
}
