package world_map;

public class Position {
    public final int coordinate_X;
    public final int coordinate_Y;

    public Position(int coordinate_X, int coordinate_Y) {
        this.coordinate_X = coordinate_X;
        this.coordinate_Y = coordinate_Y;
    }

    @Override
    public String toString() {
        return "Position{"
                + coordinate_X
                + " "
                + coordinate_Y
                + '}';
    }
}
