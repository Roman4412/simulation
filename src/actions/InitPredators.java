package actions;

import entities.Land;
import entities.Predator;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;

public class InitPredators extends Initializing {
    private final int speed;
    public InitPredators(int amount, int speed) {
        this.amount = amount;
        this.speed = speed;
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position randomPos = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(randomPos, new Predator(randomPos, speed));
            counter++;
        }
    }
}
