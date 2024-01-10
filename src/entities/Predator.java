package entities;

import world_map.Position;
import world_map.WorldMap;


public class Predator extends Creature {
    private static final int HEALTH = 15;
    private static final int SPEED = 1;
    private static final int ATTACK = 5;

    public Predator(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public boolean isFood(Position pos, WorldMap map) {
        return map.getMap().get(pos) instanceof Herbivore;
    }

}
