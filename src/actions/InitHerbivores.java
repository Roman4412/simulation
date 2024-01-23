package actions;

import entities.Herbivore;
import entities.Land;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;

public class InitHerbivores extends Initializing {
    private final int speed;

    public InitHerbivores(int amount, int speed) {
        super(amount);
        this.speed = speed;
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position randomPos = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(randomPos, new Herbivore(randomPos, speed));
            counter++;
        }
    }
}