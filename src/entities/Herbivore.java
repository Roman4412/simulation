package entities;

import world_map.Position;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 3;

    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove() {

    }
}
