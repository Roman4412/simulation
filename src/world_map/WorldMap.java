package world_map;

import entities.*;

import java.util.*;

public class WorldMap {
    Random random = new Random();
    private final int size;

    private final List<Position> allPositions;
    private Map<Position, Entity> map;
    /*
    setEntityToPos methods - are temporary methods.
    Their will be deleted
    */


    public WorldMap(int size) {
        //TODO убрать метод из конструктора.
        // Мб генерировать позиции в процессе добавления элементов и
        // получать доступные позиции через keySet?
        // шаблон builder для сборки worldMap?
        this.size = size;
        this.map = new HashMap<>();
        this.allPositions = calculateAllPositions();

    }

    public List<Position> calculateAllPositions() {
        int totalCells = size*size;
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


    public void setEntities(int predators, int herbivores, int grass, int rocks, int trees) {
        int totalCells = size * size;
        int totalEntities = predators + herbivores + grass + rocks + trees;
        if (totalCells < totalEntities) {
            throw new RuntimeException("Существ больше, чем клеток на карте");
        }

        setPredator(predators);
        setHerbivore(herbivores);
        setGrass(grass);
        setRock(rocks);
        setTree(trees);
        setLand(size * size - totalEntities);
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
       /* for (Position position : allPositions) {
            map.put(position, new Land(position));
        }*/
    }

    public void setHerbivore(int amount) {
        int count = 0;
        while (count < amount) {
            Position position = allPositions.get(random.nextInt(allPositions.size()));
            if (allPositions.contains(position)) {
                map.put(position, new Herbivore(position));
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

    public void setEntityToPos(Position pos, Herbivore h) {
        h.changePosition(pos);
        map.put(pos, h);
    }

    public void setEntityToPos(Position pos, Grass g) {
        g.changePosition(pos);
        map.put(pos, g);
    }

    public void setEntityToPos(Position pos, Tree t) {
        t.changePosition(pos);
        map.put(pos, t);
    }

    public void setEntityToPos(Position pos, Land l) {
        l.changePosition(pos);
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
