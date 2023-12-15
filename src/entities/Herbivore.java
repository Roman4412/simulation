package entities;

import world_map.Position;
import world_map.WorldMap;

import java.util.*;

public class Herbivore extends Creature {
    private static final int HEALTH = 10;
    private static final int SPEED = 1;

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


        или

        доработать findFood()
        добавляем все пройденные вершины в коллекцию
        вычисляем необходимую для хода клетку на основе удаленности от текущей позиции животного
        и позиции еды
        */
        Position target = findFoodPosition(map);
        Queue<Position> way = new ArrayDeque<>();
        List<Position> closed = new ArrayList<>();
        Queue<Position> open = new PriorityQueue<>((a,b) -> {
            int aToTarget = Math.abs(a.v - target.v) + Math.abs(a.h - target.h);
            int bToTarget = Math.abs(b.v - target.v) + Math.abs(b.h - target.h);
            return aToTarget-bToTarget;
        });
        open.add(position);
        while (!open.isEmpty()) {
            Position cell = open.poll();
            if(!cell.equals(position)) {
                way.add(cell);
            }
            if (cell.equals(target)) {
                break;
            }
            open.addAll(calcAdjacentCells(closed, cell));
        }
        map.setEntityToPos(position, new Land(position));
        Position newPosition = way.poll();
        position = newPosition;
        map.setEntityToPos(newPosition,this);
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
                current.addAll(calcAdjacentCells(processed, cell));
            }
        }
        return null;
    }

    // return new open queue
    public List<Position> calcAdjacentCells(List<Position> processed, Position cell) {
        // TODO limit to map size
        // TODO method ignores barriers
        List<Position> adjacentCells = new ArrayList<>();
        adjacentCells.add(new Position(cell.v + 1, cell.h));
        adjacentCells.add(new Position(cell.v - 1, cell.h));
        adjacentCells.add(new Position(cell.v, cell.h + 1));
        adjacentCells.add(new Position(cell.v, cell.h - 1));
        adjacentCells.removeIf(processed::contains);
        processed.addAll(adjacentCells);
        return adjacentCells;
    }
}
