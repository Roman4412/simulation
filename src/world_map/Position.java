package world_map;


import java.util.Objects;

public class Position {
    public final int horizontal;
    public final int vertical;

    public Position(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        return horizontal + "," + vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontal == position.horizontal && vertical == position.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }
}
