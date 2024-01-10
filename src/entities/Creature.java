package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static world_map.Position.*;

public abstract class Creature extends Entity {
    final int health;
    final int speed;
    Queue<Position> path = new ArrayDeque<>();

    public Creature(int health, int speed, Position position) {
        super(position);
        this.health = health;
        this.speed = speed;
    }

    public void makeMove(WorldMap map) {
        Position food = findFood(map);
        if (path.isEmpty()) {
            path = findPath(map, food);
        }
        Position posForMove = path.poll();

        if (isFood(posForMove, map)) {
            eat(map, posForMove);
        } else {
            makeStep(map, posForMove);
        }
    }

    Position findFood(WorldMap map) {
        //System.out.println("findFoodEnd start");
        Set<Position> processed = new HashSet<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        int count = 0;
        while (!current.isEmpty()) {
            Position pos = current.poll();
            if (isFood(pos, map)) {
                //   System.out.println("findFoodEnd end");
                return pos;
            } else {
                processed.add(pos);
                current.addAll(findAvailableNeighborPositions(processed, pos, map));
                count++;
            }
            if(count > 200) {
                return null;
            }
        }
        return null;
    }

    Queue<Position> findPath(WorldMap map, Position food) {
        //  System.out.println("findPath start");
        if (food == null) {
            Random random = new Random();
            List<Position> positions = findAvailableNeighborPositions(new HashSet<>(), position, map);
            Queue<Position> randomPath = new ArrayDeque<>();
            randomPath.add(positions.get(random.nextInt(positions.size())));
            return randomPath;
        } else {
            Queue<Position> pathToFood = new ArrayDeque<>();
            Set<Position> processed = new HashSet<>();
            Queue<Position> open = new PriorityQueue<>((p1, p2) -> {
                p1.finalCost = Math.abs(p1.v - food.v) + Math.abs(p1.h - food.h) + p1.baseCost;
                p2.finalCost = Math.abs(p2.v - food.v) + Math.abs(p2.h - food.h) + p2.baseCost;
                return p1.finalCost - p2.finalCost;
            });
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

            //System.out.println("pathToFood: " + pathToFood);
            // System.out.println("processed: " + processed);
            // System.out.println("open: " + open);

            return pathToFood;
        }
    }

    List<Position> findAvailableNeighborPositions(Set<Position> processed, Position pos, WorldMap map) {
        //System.out.println("findAvailable start");
        //TODO is it better to take already created Position from map?

        List<Position> neighborPos = List.of(
                new Position(pos.v + speed, pos.h, VERTICAL_MOVE_COST),
                new Position(pos.v - speed, pos.h, VERTICAL_MOVE_COST),
                new Position(pos.v, pos.h + speed, VERTICAL_MOVE_COST),
                new Position(pos.v, pos.h - speed, VERTICAL_MOVE_COST),

                new Position(pos.v + speed, pos.h - speed, DIAGONAL_MOVE_COST),
                new Position(pos.v + speed, pos.h + speed, DIAGONAL_MOVE_COST),
                new Position(pos.v - speed, pos.h - speed, DIAGONAL_MOVE_COST),
                new Position(pos.v - speed, pos.h + speed, DIAGONAL_MOVE_COST));

        List<Position> positions = neighborPos.stream()
                .filter(p -> !processed.contains(p)
                        && (p.v <= map.getSize()) && (p.v > 0)
                        && (p.h <= map.getSize()) && (p.h > 0)
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
