package world_map;

public class Position {
    public final int coordinateX;
    public final int coordinateY;

    public Position(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "Position{"
                + coordinateX
                + " "
                + coordinateY
                + '}';
    }
}
