package actions;

import entities.Land;
import entities.Rock;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;

public class InitRocks extends Initializing {
    public InitRocks(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position randomPos = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(randomPos, new Rock(randomPos));
            counter++;
        }
    }
}
