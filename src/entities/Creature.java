package entities;

import world_map.AStarPathfinding;
import world_map.Position;
import world_map.WorldMap;

import java.util.*;

public abstract class Creature extends Entity {
    protected int speed;
    protected Deque<Position> path = new ArrayDeque<>();
    AStarPathfinding AStarPathfinding = new AStarPathfinding();

    public Creature(Position position, int speed) {
        super(position);
        this.speed = speed;
    }

    public abstract boolean isFood(Position pos, WorldMap map);

    public void makeMove(WorldMap map) {
        path = AStarPathfinding.findActualPath(map, this);
        Position posForMove = path.poll();
        if (isFood(posForMove, map)) {
            eat(map, posForMove);
        } else {
            makeStep(map, posForMove);
        }
    }

    private void makeStep(WorldMap map, Position target) {
        Entity e2 = map.getMap().get(target);
        map.setEntityToPos(position, e2);
        map.setEntityToPos(target, this);
    }

    private void eat(WorldMap map, Position target) {
        Position tmp = position;
        map.setEntityToPos(target, this);
        map.setEntityToPos(tmp, new Land(tmp));
    }

    public List<Position> getPath() {
        return new ArrayList<>(path);
    }

    public int getSpeed() {
        return speed;
    }
}
