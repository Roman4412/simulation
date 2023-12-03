package world_map;

import entities.Entity;
import entities.Herbivore;
import entities.Rock;

import java.util.*;

public class WorldMap {
    private final List<Position> allPositions;
    private Map<Position, Entity> map;
    private final int size;


    public WorldMap(int size) {
        this.allPositions = calculateAllPositions(size);
        this.map = new HashMap<>();
        this.size = size;
    }

    public List calculateAllPositions(int size) {
        List<Position> positions = new ArrayList<>();
        int counterX = 1;
        int counterY = 1;
        for (int x = 0; x < size * size; x++) {
            if (counterY > size) {
                counterX++;
                counterY = 1;
            }
            positions.add(new Position(counterX, counterY++));
        }
        return positions;
    }

    Random random = new Random();

    public void setHerbivoresOnMap(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (!map.containsKey(position)) {
                map.put(position, new Herbivore(position));
                allPositions.remove(position);
                count++;
            }
        }
    }
    public void setTest(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (!map.containsKey(position)) {
                map.put(position, new Rock(position));
                allPositions.remove(position);
                count++;
            }
        }
    }

    public Map<Position, Entity> getMap() {
        return map;
    }

    public List<Position> getAllPositions() {
        return allPositions;
    }

    public int getSize() {
        return size;
    }
}
