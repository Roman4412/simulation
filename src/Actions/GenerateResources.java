package Actions;

import entities.Grass;
import entities.Herbivore;
import entities.Land;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;
import java.util.Random;

public class GenerateResources implements Action {
    //TODO интерфейс Generator
    //TODO перенести генераторы в разные классы
    Random random = new Random();

    @Override
    public void execute(WorldMap map) {
        long grassAmount = checkGrassAmount(map);
        long herbivoresAmount = checkHerbivoresAmount(map);
        if (grassAmount < 10) {
            generateGrass(map, grassAmount);
        }
        if (herbivoresAmount < 5) {
            generateHerbivores(map, herbivoresAmount);
        }
    }

    private void generateHerbivores(WorldMap map, long amount) {
        int counter = 0;
        for (int i = 0; i < 10 - amount; i++) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position position = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(position, new Herbivore(position));
            counter++;
        }
    }

    private void generateGrass(WorldMap map, long amount) {
        int counter = 0;
        for (int i = 0; i < 20 - amount; i++) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position position = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(position, new Grass(position));
            counter++;
        }
    }

    private long checkGrassAmount(WorldMap map) {
        return map.getMap().values().stream()
                .filter(value -> value instanceof Grass)
                .count();
    }

    private long checkHerbivoresAmount(WorldMap map) {
        return map.getMap().values().stream()
                .filter(value -> value instanceof Herbivore)
                .count();
    }
}
