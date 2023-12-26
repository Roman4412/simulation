package Actions;

import entities.Creature;
import entities.Entity;
import world_map.Position;
import world_map.WorldMap;

import java.util.HashMap;
import java.util.Map;

public class AllMakeMove implements Action {
    @Override
    public void execute(WorldMap map) {
        Map<Position, Entity> mapCopy = new HashMap<>(map.getMap());
        mapCopy.values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .forEach(creature -> {
                    creature.makeMove(map);
                    System.out.println("execute creature: " + creature.getPosition());
                });
    }
}
