package Actions;

import entities.Grass;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;

public class InitGrass extends InitAction implements Action {

    public InitGrass(int amount) {
        super(amount);
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Grass(position));
            map.getAllPositions().remove(position);
            counter++;
        }
    }
}
