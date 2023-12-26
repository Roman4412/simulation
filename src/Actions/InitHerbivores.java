package Actions;

import entities.Herbivore;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;

public class InitHerbivores implements Action {
    Random random = new Random();
    private int amount;

    public InitHerbivores(int amount) {
        this.amount = amount;
    }

    @Override
    public void execute(WorldMap map) {
        int count = 0;
        while (count < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Herbivore(position));
            map.getAllPositions().remove(position);
            count++;
        }
    }
}