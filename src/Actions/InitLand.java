package Actions;

import entities.Land;
import world_map.WorldMap;

public class InitLand implements Action {

    @Override
    public void execute(WorldMap map) {
        map.getAllPositions()
                .forEach(position -> map.getMap()
                        .put(position, new Land(position)));
    }
}
