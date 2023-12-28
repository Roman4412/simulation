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
        Position cellForMove = findPath(map, findFoodPosition(map)).poll();
        if (map.getMap().get(cellForMove) instanceof Grass) {
            eat(map,cellForMove);
        } else {
            makeStep(map, cellForMove);
        }
    }

    private void makeStep(WorldMap map, Position target) {
        Entity e1 = this;
        Entity e2 = map.getMap().get(target);
        map.setEntityToPos(position,e2);
        map.setEntityToPos(target,e1);
    }

    private void eat(WorldMap map, Position target) {
        Position tmp = position;
        map.setEntityToPos(target, this);
        map.setEntityToPos(tmp, new Land(tmp));
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
            List<Position> cellsForMove = findNearestCellsForMove(new ArrayList<>(), position, map);
            Queue<Position> randomPath = new ArrayDeque<>();
            randomPath.add(cellsForMove.get(random.nextInt(cellsForMove.size())));
            return randomPath;
        } else {
            Queue<Position> pathToFood = new ArrayDeque<>();
            List<Position> processed = new ArrayList<>();
            Queue<Position> open = new PriorityQueue<>((p1, p2) -> {
                int p1toFoodDistance = Math.abs(p1.v - food.v) + Math.abs(p1.h - food.h);
                int p2toFoodDistance = Math.abs(p2.v - food.v) + Math.abs(p2.h - food.h);
                return p1toFoodDistance - p2toFoodDistance;
            });

            open.add(position);
            while (!open.isEmpty()) {
                Position cell = open.poll();
                if (!pathToFood.contains(cell) && cell != position) {
                    pathToFood.add(cell);
                }
                if (cell.equals(food)) {
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
                .filter(p -> !processed.contains(p)
                                && p.v <= map.getSize() && p.v > 0
                                && p.h <= map.getSize() && p.h > 0
                                && map.getMap().get(p) instanceof Land
                                || map.getMap().get(p) instanceof Grass)
                .collect(Collectors.toList());
        processed.addAll(result);
        return result;
    }

}
