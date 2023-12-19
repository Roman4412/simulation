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

        /*
            Неверно рассчитывает путь если встречаются препятствия.
            На текущий момент алгоритм просматривает только один кратчайший путь.
            Необходимо просматривать несколько путей, затем вычислять кратчайший и именно его возвращать из функции

        */
        if (path.isEmpty()) {
            path = findPath(map);
        }
        Position cellForTurn = path.poll();
        if(map.getEntityFromPosition(cellForTurn) instanceof Grass) {

            Position tmp = position;
            map.setEntityToPos(cellForTurn,this);
            map.setEntityToPos(tmp,new Land(tmp));

        } else {
            map.swapEntities(position, cellForTurn);
        }
    }

    public Position findFoodPosition(WorldMap map) {
        List<Position> processed = new ArrayList<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        while (!current.isEmpty()) {
            Position cell = current.poll();
            if (map.getEntityFromPosition(cell) instanceof Grass) {
                return cell;
            } else {
                processed.add(cell);
                current.addAll(findAdjacentCells(processed, cell, map));
            }
        }
        return null;
    }

    public Queue<Position> findPath(WorldMap map) {
        Position target = findFoodPosition(map);
        Queue<Position> path = new ArrayDeque<>();
        List<Position> processed = new ArrayList<>();
        Queue<Position> open = new PriorityQueue<>((a, b) -> {
            int aManhDistance = Math.abs(a.v - target.v) + Math.abs(a.h - target.h);
            int bManhDistance = Math.abs(b.v - target.v) + Math.abs(b.h - target.h);
            return aManhDistance - bManhDistance;
        });

        open.add(position);
        while (!open.isEmpty()) {
            Position cell = open.poll();
            if (!path.contains(cell) && cell != position) {
                path.add(cell);
            }
            if (cell.equals(target)) {
                break;
            }
            processed.add(cell);
            open.addAll(findAdjacentCells(processed, cell, map));
        }

        System.out.println("Path: " + path);
        return path;
    }

    // return new open queue
    public List<Position> findAdjacentCells(List<Position> processed, Position cell, WorldMap map) {
        // TODO limit to map size
        // TODO method ignores barriers  +FIX
        List<Position> cells2 = List.of(
                new Position(cell.v + 1, cell.h),
                new Position(cell.v - 1, cell.h),
                new Position(cell.v, cell.h + 1),
                new Position(cell.v, cell.h - 1)
        );
        List<Position> result = cells2.stream()
                .filter(
                        p -> !processed.contains(p)
                                && p.v < map.getSize() && p.v > 1
                                && p.h < map.getSize() && p.h > 1
                                && !(map.getEntityFromPosition(p) instanceof Tree)
                                && !(map.getEntityFromPosition(p) instanceof Rock)
                                && !(map.getEntityFromPosition(p) instanceof Predator)
                                && !(map.getEntityFromPosition(p) instanceof Herbivore)

                )
                .collect(Collectors.toList());
        processed.addAll(cells2);
        System.out.println("findAdjacentCells: " + result);
        return result;


        // stable version

        /*List<Position> cells = new ArrayList<>();
        cells.add(new Position(cell.v + 1, cell.h));
        cells.add(new Position(cell.v - 1, cell.h));
        cells.add(new Position(cell.v, cell.h + 1));
        cells.add(new Position(cell.v, cell.h - 1));
        cells.removeIf(processed::contains);
        processed.addAll(cells);
        return cells;*/
    }
}
