package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.stream.Collectors;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 1;

    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove(WorldMap map) {
        Position cellForTurn = findPath(map, findFoodPosition(map)).poll();
        if (map.getMap().get(cellForTurn) instanceof Grass) {
            Position tmp = position;
            map.setEntityToPos(cellForTurn, this);
            map.setEntityToPos(tmp, new Land(tmp));
        } else {
            map.swapEntities(position, cellForTurn);
        }
    }

    public Position findFoodPosition(WorldMap map) {
        List<Position> processed = new ArrayList<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        while (!current.isEmpty()) {
            Position cell = current.poll();
            if (map.getMap().get(cell) instanceof Grass) {
                return cell;
            } else {
                processed.add(cell);
                current.addAll(findNearestCellsForMove(processed, cell, map));
            }
        }
        return null;
    }

    public Queue<Position> findPath(WorldMap map, Position food) {
        if (food == null) {
            Random random = new Random();
            List<Position> cellsForStep = findNearestCellsForMove(new ArrayList<>(), position, map);
            Queue<Position> randomPath = new ArrayDeque<>();
            randomPath.add(cellsForStep.get(random.nextInt(cellsForStep.size())));
            return randomPath;
        } else {
            Position target = food;
            Queue<Position> pathToFood = new ArrayDeque<>();
            List<Position> processed = new ArrayList<>();
            Queue<Position> open = new PriorityQueue<>((p1, p2) -> {
                int aManhDistance = Math.abs(p1.v - target.v) + Math.abs(p1.h - target.h);
                int bManhDistance = Math.abs(p2.v - target.v) + Math.abs(p2.h - target.h);
                return aManhDistance - bManhDistance;
            });

            open.add(position);
            while (!open.isEmpty()) {
                Position cell = open.poll();
                if (!pathToFood.contains(cell) && cell != position) {
                    pathToFood.add(cell);
                }
                if (cell.equals(target)) {
                    break;
                }
                processed.add(cell);
                open.addAll(findNearestCellsForMove(processed, cell, map));
            }
            return pathToFood;
        }
    }

    public List<Position> findNearestCellsForMove(List<Position> processed, Position cell, WorldMap map) {
        List<Position> cellsForStep = List.of(
                new Position(cell.v + 1, cell.h),
                new Position(cell.v - 1, cell.h),
                new Position(cell.v, cell.h + 1),
                new Position(cell.v, cell.h - 1)
        );
        List<Position> result = cellsForStep.stream()
                .filter(
                        p -> !processed.contains(p)
                                && p.v <= map.getSize() && p.v > 0
                                && p.h <= map.getSize() && p.h > 0
                                && map.getMap().get(p) instanceof Land
                                || map.getMap().get(p) instanceof Grass
                )
                .collect(Collectors.toList());
        processed.addAll(result);
        return result;
    }
}
