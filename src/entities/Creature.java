package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.stream.Collectors;

import static world_map.WorldMap.findChebyshevDistance;

public abstract class Creature extends Entity {
    final int health;
    final int speed;
    Deque<Position> path = new ArrayDeque<>();

    public Creature(int health, int speed, Position position) {
        super(position);
        this.health = health;
        this.speed = speed;
    }

    public void makeMove(WorldMap map) {
        //TODO redo cast to Deque
        Position food = findFood(map);
        if (!path.isEmpty() && food != null) {
            int oldFoodCost = findChebyshevDistance(position,path.peekLast());
            int newFoodCost = findChebyshevDistance(position,food);
            if (newFoodCost < oldFoodCost) {
                path = (Deque<Position>) findPath(map, food);
            }
        } else {
            path = (Deque<Position>) findPath(map, food);
        }
        Position posForMove = path.poll();
        if (isFood(posForMove, map)) {
            eat(map, posForMove);
        } else {
            makeStep(map, posForMove);
        }
    }

    Position findFood(WorldMap map) {
        return map.getMap().keySet().stream()
                .filter(pos -> isFood(pos, map))
                .min((pos1, pos2) -> {
                    int maxForA = findChebyshevDistance(pos1,position);
                    int maxForB = findChebyshevDistance(pos2,position);
                    return maxForA - maxForB;
                })
                .orElse(null);
    }


    Queue<Position> findPath(WorldMap map, Position food) {
        if (food == null) {
            Random random = new Random();
            List<Position> positions = findAvailableNeighborPositions(new HashSet<>(), position, map);
            Queue<Position> randomPath = new ArrayDeque<>();
            randomPath.add(positions.get(random.nextInt(positions.size())));
            return randomPath;
        } else {
            Queue<Position> pathToFood = new ArrayDeque<>();
            Set<Position> processed = new HashSet<>();
            Queue<Position> open = new PriorityQueue<>((a, b) -> {
                int maxForA = findChebyshevDistance(a,food);
                int maxForB = findChebyshevDistance(b,food);
                return maxForA - maxForB;
            }
            );
            open.add(position);
            while (!open.isEmpty()) {
                Position pos = open.poll();
                if (!pathToFood.contains(pos) && pos != this.position) {
                    pathToFood.add(pos);
                }
                if (pos.equals(food)) {
                    break;
                }
                processed.add(pos);
                List<Position> availablePos = findAvailableNeighborPositions(processed, pos, map);
                // open.clear();
                processed.addAll(availablePos);
                open.addAll(availablePos);
            }
            return pathToFood;
        }
    }

    List<Position> findAvailableNeighborPositions(Set<Position> processed, Position pos, WorldMap map) {
        List<Position> neighborPositions = new ArrayList<>();
        for (int i = pos.horizontal - speed; i <= pos.horizontal + speed; i++) {
            for (int j = pos.vertical - speed; j <= pos.vertical + speed; j++) {
                neighborPositions.add(new Position(i,j));
                }
            }

        return neighborPositions.stream()
                .filter(p -> !processed.contains(p)
                        && (p.horizontal <= map.getSize()) && (p.horizontal > 0)
                        && (p.vertical <= map.getSize()) && (p.vertical > 0)
                        && map.getMap().get(p) instanceof Land
                        || isFood(p, map))
                .collect(Collectors.toList());
    }

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

    abstract boolean isFood(Position pos, WorldMap map);

}
