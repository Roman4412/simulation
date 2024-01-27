package entities;

import world_map.Position;
import world_map.WorldMap;


public class Predator extends Creature {
    public Predator(Position pos, int speed) {
        super(pos, speed);
    }

    @Override
    public boolean isFood(Position pos, WorldMap map) {
        return map.getMap().get(pos) instanceof Herbivore;
    }

}
