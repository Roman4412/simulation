package world_map;

import java.util.Objects;

public class Position {
    public final int v;
    public final int h;

    public Position(int v, int h) {
        this.v = v;
        this.h = h;
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
