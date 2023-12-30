package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Creature extends Entity {
    final int health;
    final int speed;

    public Creature(int health, int speed, Position position) {
        super(position);
        this.health = health;
        this.speed = speed;
    }

    public void makeMove(WorldMap map) {
        Position food = findFood(map);
        // System.out.println(posForMove);

        Position posForMove = findPath(map, findFood(map)).poll();
        // System.out.println("pdr from: " + position + " to: " + posForMove);
        if (isFood().test(map.getMap().get(posForMove))) {
            // attack
            eat(map, posForMove);
        } else {
            makeStep(map, posForMove);
        }
    }

    Position findFood(WorldMap map) {
        Set<Position> processed = new HashSet<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        while (!current.isEmpty()) {
            Position cell = current.poll();
            if (isFood().test(map.getMap().get(cell))) {
                return cell;
            } else {
                processed.add(cell);
                current.addAll(findAvailableNeighborPositions(processed, cell, map));
            }
        }
        return null;
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
            Queue<Position> open = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.findFinalCost(food)));
            open.add(position);
            while (!open.isEmpty()) {
                Position position = open.poll();
                if (!pathToFood.contains(position) && position != this.position) {
                    pathToFood.add(position);
                }
                if (position.equals(food)) {
                    break;
                }
                processed.add(position);
                open.addAll(findAvailableNeighborPositions(processed, position, map));
                // System.out.println("position: " + position);
            }

            // System.out.println("pathToFood: " + pathToFood);
            // System.out.println("processed: " + processed);
            // System.out.println("open: " + open);
            return pathToFood;
        }
    }

    List<Position> findAvailableNeighborPositions(Set<Position> processed,
                                                  Position pos,
                                                  WorldMap map) {
        List<Position> neighborPositions = List.of(
                new Position(pos.v + speed, pos.h, 10),
                new Position(pos.v - speed, pos.h, 10),
                new Position(pos.v, pos.h + speed, 10),
                new Position(pos.v, pos.h - speed, 10),

                new Position(pos.v + speed, pos.h - speed, 14),
                new Position(pos.v + speed, pos.h + speed, 14),
                new Position(pos.v - speed, pos.h - speed, 14),
                new Position(pos.v - speed, pos.h + speed, 14)
        );
        List<Position> availablePositions = neighborPositions.stream()
                .filter(p -> !processed.contains(p)
                        && (p.v <= map.getSize()) && (p.v > 0)
                        && (p.h <= map.getSize()) && (p.h > 0)
                        && map.getMap().get(p) instanceof Land
                        || isFood().test(map.getMap().get(p)))
                .collect(Collectors.toList());
        processed.addAll(availablePositions);
        return availablePositions;
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

    abstract Predicate<Entity> isFood();
}
