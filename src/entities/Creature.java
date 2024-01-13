package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.stream.Collectors;

import static world_map.Position.*;

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
        //TODO redo cast to Deque, findCostToTarget
        Position food = findFood(map);
        if (!path.isEmpty() && food != null) {
            //TODO заменить findCostToTarget вместе с компараторами на один метод findChebyshevDistance
            int oldFoodCost = position.findChebyshevDistance(path.peekLast());
            int newFoodCost = position.findChebyshevDistance(food);
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
                    int maxForA = pos1.findChebyshevDistance(position);
                    int maxForB = pos2.findChebyshevDistance(position);;
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
                int maxForA = a.findChebyshevDistance(food);
                int maxForB = b.findChebyshevDistance(food);
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
                open.clear();
                processed.addAll(availablePos);
                open.addAll(availablePos);
            }
            return pathToFood;
        }
    }

    List<Position> findAvailableNeighborPositions(Set<Position> processed, Position pos, WorldMap map) {
        //TODO is it better to take already created Position from map?

        List<Position> neighborPos = List.of(
                new Position(pos.vertical + speed, pos.horizontal, VERTICAL_MOVE_COST),
                new Position(pos.vertical - speed, pos.horizontal, VERTICAL_MOVE_COST),
                new Position(pos.vertical, pos.horizontal + speed, VERTICAL_MOVE_COST),
                new Position(pos.vertical, pos.horizontal - speed, VERTICAL_MOVE_COST),

                new Position(pos.vertical + speed, pos.horizontal - speed, DIAGONAL_MOVE_COST),
                new Position(pos.vertical + speed, pos.horizontal + speed, DIAGONAL_MOVE_COST),
                new Position(pos.vertical - speed, pos.horizontal - speed, DIAGONAL_MOVE_COST),
                new Position(pos.vertical - speed, pos.horizontal + speed, DIAGONAL_MOVE_COST));

        List<Position> positions = neighborPos.stream()
                .filter(p -> !processed.contains(p)
                        && (p.vertical <= map.getSize()) && (p.vertical > 0)
                        && (p.horizontal <= map.getSize()) && (p.horizontal > 0)
                        && map.getMap().get(p) instanceof Land
                        || isFood(p, map))
                .collect(Collectors.toList());
        return positions;
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
