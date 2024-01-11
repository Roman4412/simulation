package Actions;

import entities.Grass;
import entities.Herbivore;
import entities.Land;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;
import java.util.Random;

public class GenerateResources implements Action {
    Random random = new Random();

    @Override
    public void execute(WorldMap map) {
        long grassAmount = checkGrassAmount(map);
        if (grassAmount < 8) {
            System.out.println("Grass amo: " + grassAmount);
            generateGrass(map, grassAmount);
        }
        if (checkHerbivoresAmount(map) < 5) {
            System.out.println("Herbivores amo: " + checkHerbivoresAmount(map));
            //generateHerbivores(map);
        }
    }

    private void generateGrass(WorldMap map, long amount) {
        int counter = 0;
        for (int i = 0; i < 10 - amount; i++) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position position = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(position, new Grass(position));
            counter++;
        }
        System.out.println("Grass generated: " + counter);
    }

    private long checkGrassAmount(WorldMap map) {
        long count = map.getMap().values().stream().filter(value -> value instanceof Grass).count();
        return count;
    }

    private long checkHerbivoresAmount(WorldMap map) {
        long count = map.getMap().values().stream().filter(value -> value instanceof Herbivore).count();
        return count;
    }

}
