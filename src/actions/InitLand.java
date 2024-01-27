package actions;

import entities.Land;
import world_map.WorldMap;


public class InitLand extends Initializing {

    @Override
    public void execute(WorldMap map) {
        map.getMap().keySet().forEach(key -> map.setEntityToPos(key, new Land(key)));
    }
}
