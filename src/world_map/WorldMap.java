package world_map;

import entities.Entity;

import java.util.*;

public class WorldMap {
    Set<String> l = new HashSet<>();

    private final Position[] allPositions;
    private Map<Position, Entity> map;


    public WorldMap(int size) {
        this.allPositions = calculateAllPositions(size);
        this.map = new HashMap<>();
    }

    public Position[] calculateAllPositions(int size) {
        Position[] positions = new Position[size * size];
        int counterX = 1;
        int counterY = 1;
        for (int x = 0; x < size * size; x++) {
            if (counterY > 10) {
                counterX++;
                counterY = 1;
            }
            positions[x] = new Position(counterX, counterY++);
        }
        return positions;
    }

    public Map<Position, Entity> getMap() {
        return map;
    }

    public Position[] getAllPositions() {
        return allPositions;
    }
}
