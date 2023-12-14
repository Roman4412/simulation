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
        */
    }

    public Position findFoodPosition(WorldMap map) {
        List<Position> processed = new ArrayList<>();
        Queue<Position> current = new ArrayDeque<>(List.of(position));
        while (!current.isEmpty()) {
            Position cell = current.poll();
            System.out.println(cell);
            if (map.getEntityFromPosition(cell) instanceof Grass) {
                return cell;
                // return nearest cell to grass
            } else {
                processed.add(cell);
                current.addAll(calcAdjacentCells(processed, cell));
            }
        }
        // return random nearest cell
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

    public static int calculateDistanceBetweenCells(Position current, Position target) {
        return Math.abs(current.v - target.v) + Math.abs(current.h - target.h);
    }
}
