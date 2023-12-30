package entities;


import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Predator extends Creature {
    private static final int HEALTH = 15;
    private static final int SPEED = 1;
    private static final int ATTACK = 5;
    Queue<Position> path = new ArrayDeque<>();
    Predicate<Entity> isAvailable = pos -> pos instanceof Herbivore || pos instanceof Land;

    public Predator(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove(WorldMap map) {
        Position food = findFood(map);
        // System.out.println(posForMove);
        path = findPath(map, food);
        Position posForMove = path.poll();
        System.out.println("pdr from: " + position + " to: " + posForMove);
        if (map.getMap().get(posForMove) instanceof Herbivore) {
            eat(map,posForMove);
        } else {
            makeStep(map, posForMove);
        }
    }

    @Override
    public Position findFood(WorldMap map) {
        Set<Position> processed = new HashSet<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        while (!current.isEmpty()) {
            Position cell = current.poll();
            if (map.getMap().get(cell) instanceof Herbivore) {
                return cell;
            } else {
                processed.add(cell);
                current.addAll(findAvailableNeighborPositions(processed, cell, map, isAvailable));
            }
        }
        return null;
    }

    @Override
    public Queue<Position> findPath(WorldMap map, Position food) {
        if (food == null) {
            Random random = new Random();
            List<Position> positions = findAvailableNeighborPositions(new HashSet<>(), position, map, isAvailable);
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
                open.addAll(findAvailableNeighborPositions(processed, position, map, isAvailable));
                // System.out.println("position: " + position);
            }

            // System.out.println("pathToFood: " + pathToFood);
            // System.out.println("processed: " + processed);
            // System.out.println("open: " + open);
            return pathToFood;
        }
    }

    @Override
    public List<Position> findAvailableNeighborPositions(Set<Position> processed,
                                                         Position pos,
                                                         WorldMap map,
                                                         Predicate<Entity> isAvailable) {
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
                        && isAvailable.test(map.getMap().get(p)))
                .collect(Collectors.toList());
        processed.addAll(availablePositions);
        return availablePositions;
    }

}
