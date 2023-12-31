package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.Map;

public abstract class Creature extends Entity {
    final int health;
    final int speed;

    public Creature(int health, int speed, Position position) {
        super(position);
        this.health = health;
        this.speed = speed;
    }

    abstract void makeMove(WorldMap map);
    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

}
