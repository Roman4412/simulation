package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;

public class Herbivore extends Creature {

    private static final int HEALTH = 10;
    private static final int SPEED = 1;

    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove(WorldMap map) {

    }

    public Position findFood(WorldMap map) {
        Queue<Position> closed = new ArrayDeque<>();
        Queue<Position> open = new ArrayDeque<>(getNeighbor(position, closed));
        closed.add(position);
        while (!closed.isEmpty()) {
            while (!open.isEmpty()) {
                Position elmnt = open.poll();
                closed.add(elmnt);
                if (map.getEntityFromPosition(elmnt) instanceof Grass) {
                    return elmnt;
                }
            }
            open.addAll(getNeighbor(closed.poll(), closed));
        }
        return null;
    }

    public List<Position> getNeighbor(Position position, Queue closed) {
        // TODO add constraints
        List<Position> result = new ArrayList<>();
        result.add(new Position(position.x + 1, position.y));
        result.add(new Position(position.x - 1, position.y));
        result.add(new Position(position.x, position.y + 1));
        result.add(new Position(position.x, position.y - 1));
        result.add(new Position(position.x + 1, position.y + 1));
        result.add(new Position(position.x + 1, position.y - 1));
        result.add(new Position(position.x - 1, position.y + 1));
        result.add(new Position(position.x - 1, position.y - 1));
        result.removeIf(closed::contains);
        return result;
    }

}
