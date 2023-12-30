package entities;

import world_map.Position;

import java.util.function.Predicate;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 1;
    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public Predicate<Entity> isFood() {
        return pos -> pos instanceof Grass;
    }

}
