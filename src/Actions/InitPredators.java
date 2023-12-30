package Actions;

import entities.Predator;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;

public class InitPredators extends InitAction implements Action {
    public InitPredators(int amount) {
        this.amount = amount;
    }
    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Predator(position));
            map.getAllPositions().remove(position);
            counter++;
        }
    }
}
