package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.function.Predicate;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 1;
    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public boolean isFood(Position pos, WorldMap map) {
        return map.getMap().get(pos) instanceof Grass;
    }

}
