package Actions;

import entities.Rock;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;

public class InitRocks extends InitAction implements Action{
    public InitRocks(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Rock(position));
            map.getAllPositions().remove(position);
            counter++;
        }
    }
}
