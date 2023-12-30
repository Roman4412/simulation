package Actions;

import entities.Tree;
import world_map.Position;
import world_map.WorldMap;

public class InitTrees extends InitAction implements Action {
    public InitTrees(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Tree(position));
            map.getAllPositions().remove(position);
            counter++;
        }
    }
}
