package world_map;

import entities.*;

import java.util.*;
import java.util.stream.Stream;

public class WorldMap {
    private final int size;
    private final Map<Position, Entity> map;

    public WorldMap(int size) {
        this.size = size;
        this.map = initPositions();
    }

    public Map<Position,Entity> initPositions() {
        int totalCells = size * size;
        Map<Position, Entity> map = new HashMap<>();
        int counterX = 1;
        int counterY = 1;
        for (int x = 0; x < totalCells; x++) {
            if (counterY > size) {
                counterX++;
                counterY = 1;
            }
            map.put(new Position(counterX, counterY++),null);
        }
        return map;
    }

    public void setEntityToPos(Position pos, Entity e) {
        e.setPosition(pos);
        map.put(pos, e);
    }

    public Map<Position, Entity> getMap() {
        return Collections.unmodifiableMap(map);
    }

    public static int findChebyshevDistance(Position start, Position target) {
        return Math.max(Math.abs(start.getX() - target.getX()),
                Math.abs(start.getY() - target.getY()));
    }

    public int getSize() {
        return size;
    }

    public long getEntitiesAmount(Class<? extends Entity> type) {
        Stream<Entity> entityStream = map.values().stream();
        switch (type.getSimpleName()) {
            case "Predator":
                return entityStream.filter(h -> h instanceof Predator).count();
            case "Herbivore":
                return entityStream.filter(h -> h instanceof Herbivore).count();
            case "Rock":
                return entityStream.filter(h -> h instanceof Rock).count();
            case "Grass":
                return entityStream.filter(h -> h instanceof Grass).count();
            case "Tree":
                return entityStream.filter(h -> h instanceof Tree).count();
            case "Land":
                return entityStream.filter(h -> h instanceof Land).count();
            default:
                return -1;
        }
    }
}
