package world_map;

import entities.*;

import java.util.*;

public class WorldMap {
    //TODO перенести методы в интерфейс
    private final int size;
    private final List<Position> allPositions;
    private Map<Position, Entity> map;

    public WorldMap(int size) {
        this.size = size;
        this.map = new HashMap<>();
        this.allPositions = calculateAllPositions();

    }

    public List<Position> calculateAllPositions() {
        int totalCells = size * size;
        List<Position> positions = new ArrayList<>();
        int counterX = 1;
        int counterY = 1;
        for (int x = 0; x < totalCells; x++) {
            if (counterY > size) {
                counterX++;
                counterY = 1;
            }
            positions.add(new Position(counterX, counterY++));
        }
        return positions;
    }

    public void setEntityToPos(Position pos, Entity e) {
        e.setPosition(pos);
        map.put(pos, e);
    }

    public Map<Position, Entity> getMap() {
        return map;
    }

    public List<Position> getAllPositions() {
        return allPositions;
    }

    public static int findChebyshevDistance(Position start, Position target) {
        return Math.max(Math.abs(start.getHorizontal() - target.getHorizontal()),
                Math.abs(start.getVertical() - target.getVertical()));
    }

    public int getSize() {
        return size;
    }

}
