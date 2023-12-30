package world_map;


import java.util.Objects;

public class Position {
    public final int v;
    public final int h;
    public int costToFinalPos;
    public int costFromStartPos;

    public Position(int v, int h) {
        this.v = v;
        this.h = h;
    }

    public Position(int v, int h, int costToFinalPos) {
        this.v = v;
        this.h = h;
        this.costToFinalPos = costToFinalPos;
    }

    public int findFinalCost(Position food) {
        return Math.abs(v - food.v) + Math.abs(h - food.h) + costFromStartPos;
    }


    @Override
    public String toString() {
        return v + "," + h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return v == position.v && h == position.h;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, h);
    }
}
