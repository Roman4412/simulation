package Actions;

import entities.Herbivore;
import world_map.Position;
import world_map.WorldMap;

public class InitHerbivores extends  InitAction implements Action {

    public InitHerbivores(int amount) {
        super(amount);
    }

    @Override
    public void execute(WorldMap map) {
        while (counter < amount) {
            Position position = map.getAllPositions().get(random.nextInt(map.getAllPositions().size()));
            map.getMap().put(position, new Herbivore(position));
            map.getAllPositions().remove(position);
            counter++;
        }
    }
}