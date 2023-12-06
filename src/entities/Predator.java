package entities;


import world_map.Position;
import world_map.WorldMap;

import java.util.Map;

public class Predator extends Creature {
    private static final int HEALTH = 15;
    private static final int SPEED = 4;
    private static final int ATTACK = 5;


    public Predator(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove(WorldMap map) {

    }

    public int getAttack() {
        return ATTACK;
    }

}
