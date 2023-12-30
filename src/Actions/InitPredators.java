package Actions;

import entities.Herbivore;
import entities.Predator;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;

public class InitPredators implements Action {
    Random random = new Random();
    private final int amount;

    public InitPredators(int amount) {
        this.amount = amount;
    }
    @Override
    public void execute(WorldMap map) {
        int count = 0;
        while (count < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Predator(position));
            map.getAllPositions().remove(position);
            count++;
        }
    }
}
