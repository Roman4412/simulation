package world_map;

import entities.Creature;
import entities.Land;

import java.util.*;

import static world_map.WorldMap.findChebyshevDistance;

public class AStarPathfinding {
    public Deque<Position> findActualPath(WorldMap map, Creature creature) {
        Position food = findFood(map, creature);
        Deque<Position> path = new ArrayDeque<>(creature.getPath());
        if (!creature.getPath().isEmpty() && Objects.nonNull(food)) {
            int oldDistance = findChebyshevDistance(creature.getPosition(), Objects.requireNonNull(path.peekLast()));
            int newDistance = findChebyshevDistance(creature.getPosition(), food);
            if (newDistance < oldDistance) {
                path = findPath(map, food, creature);
                return path;
            }
        } else if (creature.getPath().isEmpty()) {
            path = findPath(map, food, creature);
            return path;
        }
        return path;
    }

    private Position findFood(WorldMap map, Creature creature) {
        return map.getMap().keySet().stream()
                .filter(pos -> creature.isFood(pos, map))
                .min((pos1, pos2) -> {
                    int maxForA = findChebyshevDistance(pos1, creature.getPosition());
                    int maxForB = findChebyshevDistance(pos2, creature.getPosition());
                    return maxForA - maxForB;
                })
                .orElse(null);
    }


    private Deque<Position> findPath(WorldMap map, Position food, Creature creature) {
        if (Objects.isNull(food)) {
            Random random = new Random();
            List<Position> positions = findAvailableNeighborPositions(new HashSet<>(), creature.getPosition(), map, creature);
            Deque<Position> randomPath = new ArrayDeque<>();
            randomPath.add(positions.get(random.nextInt(positions.size())));
            return randomPath;
        } else {
            Deque<Position> pathToFood = new ArrayDeque<>();
            Set<Position> processed = new HashSet<>();
            Queue<Position> open = new PriorityQueue<>((a, b) -> {
                int maxForA = findChebyshevDistance(a, food);
                int maxForB = findChebyshevDistance(b, food);
                return maxForA - maxForB;
            });
            open.add(creature.getPosition());
            while (!open.isEmpty()) {
                Position pos = open.poll();
                if (!pathToFood.contains(pos) && pos != creature.getPosition()) {
                    pathToFood.add(pos);
                }
                if (pos.equals(food)) {
                    break;
                }
                processed.add(pos);
                List<Position> availablePos = findAvailableNeighborPositions(processed, pos, map, creature);
                processed.addAll(availablePos);
                open.addAll(availablePos);
            }
            return pathToFood;
        }
    }

    private List<Position> findAvailableNeighborPositions(Set<Position> processed, Position pos, WorldMap map, Creature creature) {
        List<Position> neighborPositions = new ArrayList<>();
        for (int i = pos.getX() - creature.getSpeed(); i <= pos.getX() + creature.getSpeed(); i++) {
            for (int j = pos.getY() - creature.getSpeed(); j <= pos.getY() + creature.getSpeed(); j++) {
                Position p = new Position(i, j);
                if (!processed.contains(p)
                        && (p.getX() <= map.getSize()) && (p.getX() > 0)
                        && (p.getY() <= map.getSize()) && (p.getY() > 0)
                        && map.getMap().get(p) instanceof Land
                        || creature.isFood(p, map)
                )
                    neighborPositions.add(new Position(i, j));
            }
        }
        return neighborPositions;
    }
}
