package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

public abstract class Creature extends Entity {
    final int health;
    final int speed;

    public Creature(int health, int speed, Position position) {
        super(position);
        this.health = health;
        this.speed = speed;
    }

    public abstract void makeMove(WorldMap map);

    public abstract Position findFood(WorldMap map);

    public abstract Queue<Position> findPath(WorldMap map, Position food);

    public abstract List<Position> findAvailableNeighborPositions(Set<Position> processed,
                                                                  Position pos,
                                                                  WorldMap map,
                                                                  Predicate<Entity> isAvailable);

    void makeStep(WorldMap map, Position target) {
            Entity e2 = map.getMap().get(target);
            map.setEntityToPos(position, e2);
            map.setEntityToPos(target, this);
    }

    void eat(WorldMap map, Position target) {
        Position tmp = position;
        map.setEntityToPos(target, this);
        map.setEntityToPos(tmp, new Land(tmp));
    }
}
