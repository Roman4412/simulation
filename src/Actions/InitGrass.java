package Actions;

import entities.Grass;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;

public class InitGrass implements Action {
    Random random = new Random();

    private final int amount;

    public InitGrass(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap map) {
        int count = 0;
        while (count < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Grass(position));
            map.getAllPositions().remove(position);
            count++;

        }
    }
}
