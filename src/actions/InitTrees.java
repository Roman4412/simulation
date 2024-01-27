package actions;

import entities.Land;
import entities.Tree;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;

public class InitTrees extends Initializing {
    public InitTrees(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            List<Position> availablePositions = map.getMap().keySet().stream()
                    .filter(key -> map.getMap().get(key) instanceof Land)
                    .toList();
            Position randomPos = availablePositions.get(random.nextInt(availablePositions.size()));
            map.setEntityToPos(randomPos, new Tree(randomPos));
            counter++;
        }
    }
}
