package Actions;

import entities.Creature;
import world_map.WorldMap;

public class AllMakeMove {
    public void execute(WorldMap map) {
        map.getMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .forEach(creature -> creature.makeMove(map));
    }
}
