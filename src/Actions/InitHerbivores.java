package Actions;

import entities.Herbivore;
import entities.Land;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;

public class InitHerbivores extends Initializing {

    public InitHerbivores(int amount) {
        super(amount);
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position randomPos = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(randomPos, new Herbivore(randomPos));
            counter++;
        }
    }
}