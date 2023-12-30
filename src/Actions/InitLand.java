package Actions;

import entities.Land;
import world_map.Position;
import world_map.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class InitLand extends InitAction implements Action {

    @Override
    public void execute(WorldMap map) {
        List<Position> allPositionsCopy = new ArrayList<>(map.getAllPositions());
        allPositionsCopy.forEach(position ->  {
                    map.getMap().put(position, new Land(position));
                    map.getAllPositions().remove(position);
                });
    }
}
