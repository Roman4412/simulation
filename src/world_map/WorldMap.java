package world_map;

import entities.*;

import java.util.*;

public class WorldMap {
    private final List<Position> allPositions;
    private Map<Position, Entity> map;
    private final int size;
    /*
    setToPos methods - are temporary methods.
    Their functionality will be redesigned using random.
    */

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

    public void setEntities(int predators, int herbivores, int grass, int rocks, int trees) {
        int sum = predators + herbivores + grass + rocks + trees;
        setPredator(predators);
        setHerbivore(herbivores);
        setGrass(grass);
        setRock(rocks);
        setTree(trees);
        setLand(size * size - sum);
    }

    public void setHerbivore(int amount) {
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

    public void setLand(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (!map.containsKey(position)) {
                map.put(position, new Land(position));
                allPositions.remove(position);
                count++;
            }
        }
    }

    public void setPredator(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (!map.containsKey(position)) {
                map.put(position, new Predator(position));
                allPositions.remove(position);
                count++;
            }
        }
    }

    public void setGrass(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (!map.containsKey(position)) {
                map.put(position, new Grass(position));
                allPositions.remove(position);
                count++;
            }
        }
    }

    public void setTree(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (!map.containsKey(position)) {
                map.put(position, new Tree(position));
                allPositions.remove(position);
                count++;
            }
        }
    }

    public void setRock(int amount) {
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

    public Entity getEntityFromPosition(Position pos) {
        return map.get(pos);
    }

    public void setEntityToPos(Position pos, Herbivore h) {
        h.position = pos;
        map.put(pos, h);
    }

    public void setEntityToPos(Position pos, Grass g) {
        g.position = pos;
        map.put(pos, g);
    }

    public void setEntityToPos(Position pos, Tree t) {
        t.position = pos;
        map.put(pos, t);
    }

    public void setEntityToPos(Position pos, Land l) {
        l.position = pos;
        map.put(pos, l);
    }

    public void swapEntities(Position p1, Position p2) {
        Entity e1 = map.get(p1);
        Entity e2 = map.get(p2);

        map.replace(p1, e2);
        e2.changePosition(p1);

        map.replace(p2, e1);
        e1.changePosition(p2);
    }

    public List<Position> getAllPositions() {
        return allPositions;
    }

    public int getSize() {
        return size;
    }
}
