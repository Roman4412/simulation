package world_map;


import java.util.Objects;

public class Position {
    public final static int DIAGONAL_MOVE_COST = 14;
    public final static int VERTICAL_MOVE_COST = 10;
    public int moveCost;
    public int totalCost;
    public final int vertical;
    public final int horizontal;

    public Position(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Position(int vertical, int horizontal, int moveCost) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.moveCost = moveCost;
    }

    public int findCostToTarget(Position target) {
        totalCost = Math.abs(vertical - target.vertical)
                + Math.abs(horizontal - target.horizontal)
                + moveCost;
        return totalCost;
    }

    @Override
    public String toString() {
        return vertical + "," + horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return vertical == position.vertical && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }
}
