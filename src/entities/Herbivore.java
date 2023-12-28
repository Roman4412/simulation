package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.stream.Collectors;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 1;
    Queue<Position> path = new ArrayDeque<>();

    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove(WorldMap map) {
        Position food = findFoodPosition(map);
        if (path.isEmpty()) {
            path = findPath(map, food);
        }
        Position cellForMove = path.poll();
        System.out.println(cellForMove);
        if (map.getMap().get(cellForMove) instanceof Grass) {
            eat(map, cellForMove);
        } else {
            //System.out.println("cellToFoodDistance: " + Math.abs(cellForMove.v - food.v) + Math.abs(cellForMove.h - food.h));
            makeStep(map, cellForMove);
        }
    }

    private void makeStep(WorldMap map, Position target) {
        Entity e1 = this;
        Entity e2 = map.getMap().get(target);
        map.setEntityToPos(position, e2);
        map.setEntityToPos(target, e1);
    }

    private void eat(WorldMap map, Position target) {
        Position tmp = position;
        map.setEntityToPos(target, this);
        map.setEntityToPos(tmp, new Land(tmp));
    }

    public Position findFoodPosition(WorldMap map) {
        Set<Position> processed = new HashSet<>();
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
            List<Position> cellsForMove = findNearestCellsForMove(new HashSet<>(), position, map);
            Queue<Position> randomPath = new ArrayDeque<>();
            randomPath.add(cellsForMove.get(random.nextInt(cellsForMove.size())));
            return randomPath;
        } else {
            Queue<Position> pathToFood = new ArrayDeque<>();
            Set<Position> processed = new HashSet<>();
            Queue<Position> open = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.calcFinalCost(food)));
            /*
             * взять cell из open
             * добавить в pathToFood
             * если текущая ячейка еда - прервать цикл
             * добавить cell в обработанные
             * выбрать доступные соседние ячейки
             * вычислить расстояние для каждой соседней ячейки до еды
             * добавить в open в порядке приближения к еде
             * */
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
                //System.out.println("cell: " + cell);
            }

            // System.out.println("pathToFood: " + pathToFood);
            //  System.out.println("processed: " + processed);
            System.out.println("open: " + open);
            return pathToFood;
        }

    }


    public List<Position> findNearestCellsForMove(Set<Position> processed, Position cell, WorldMap map) {
        List<Position> nearestCells = List.of(
                new Position(cell.v + 1, cell.h, 10),
                new Position(cell.v - 1, cell.h, 10),
                new Position(cell.v, cell.h + 1, 10),
                new Position(cell.v, cell.h - 1, 10),

                new Position(cell.v + 1, cell.h - 1, 14),
                new Position(cell.v + 1, cell.h + 1, 14),
                new Position(cell.v - 1, cell.h - 1, 14),
                new Position(cell.v - 1, cell.h + 1, 14)
        );
        List<Position> result = nearestCells.stream()
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
