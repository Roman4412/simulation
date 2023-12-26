package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;
import java.util.stream.Collectors;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 1;
    private Queue<Position> path = new ArrayDeque<>();

    public Herbivore(Position pos) {
        super(HEALTH, SPEED, pos);
    }

    @Override
    public void makeMove(WorldMap map) {
        /*
        ищет еду в радиусе 5-ти клеток,
        если увидел еду - делает шаг к ней
        если не нашел еду за свой ход, двигается в рандомную клетку

        finFood() - в двойном цикле сканируем видимую для животного область
                    и ищем всю еду в этой  области
                    возвращаем ближайшую еду

        makeMove() - через А* вычисляем оптимальный путь
        PriorityQueue, в качестве компаратора разница между манхэннтонским расстоянием элементов
        */
        //TODO необходимо проверять путь каждый ход т к
        // может возникнуть ситуация, когда 2 существа попытаются занять одну клетку

        if (path.isEmpty()) {
            //System.out.println("old position: " + this.position);
            path = findPath(map);
        }
        Position cellForTurn = path.poll();
        if (map.getEntityFromPosition(cellForTurn) instanceof Grass) {
            Position tmp = position;
            map.setEntityToPos(cellForTurn, this);
            map.setEntityToPos(tmp, new Land(tmp));

        } else {
            map.swapEntities(position, cellForTurn);
            //System.out.println("new position: " + this.position);
        }
    }

    public Position findFoodPosition(WorldMap map) {
        List<Position> processed = new ArrayList<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        while (!current.isEmpty()) {
            Position cell = current.poll();
            if (map.getEntityFromPosition(cell) instanceof Grass) {
                //System.out.println("findFoodPosition: " + cell);
                return cell;
            } else {
                processed.add(cell);
                current.addAll(findAdjacentCells(processed, cell, map));
            }
        }
        //System.out.println("findFoodPosition: null");
        return null;
    }

    public Queue<Position> findPath(WorldMap map) {
        //вычисляет рандомный путь если еды нет
        if (findFoodPosition(map) == null) {
            Random random = new Random();
            List<Position> cellsForStep = findAdjacentCells(new ArrayList<>(), position, map);
            Queue<Position> randomPath = new ArrayDeque<>();
            randomPath.add(cellsForStep.get(random.nextInt(cellsForStep.size())));
            //System.out.println("randomCell: " + randomPath.peek());
            return randomPath;
        } else {
            Position target = findFoodPosition(map);
            Queue<Position> pathToFood = new ArrayDeque<>();
            List<Position> processed = new ArrayList<>();
            Queue<Position> open = new PriorityQueue<>((a, b) -> {
                int aManhDistance = Math.abs(a.v - target.v) + Math.abs(a.h - target.h);
                int bManhDistance = Math.abs(b.v - target.v) + Math.abs(b.h - target.h);
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
                open.addAll(findAdjacentCells(processed, cell, map));
            }

            //System.out.println("findPath: " + pathToFood);
            return pathToFood;
        }
    }

    public List<Position> findAdjacentCells(List<Position> processed, Position cell, WorldMap map) {
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
                                && map.getEntityFromPosition(p) instanceof Land
                                || map.getEntityFromPosition(p) instanceof Grass
                )
                .collect(Collectors.toList());
        processed.addAll(result);
        //System.out.println("findAdjacentCells: " + result);
        return result;
    }
}
