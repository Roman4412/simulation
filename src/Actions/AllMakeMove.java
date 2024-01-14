package Actions;

import entities.Creature;
import world_map.WorldMap;



public class AllMakeMove implements Action {
    @Override
    public void execute(WorldMap map) {
        //TODO вызывать makeMove() у predator после herbivore для корректного отображения
        map.getMap().values().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity)
                .toList()
                .iterator()
                .forEachRemaining(creature -> {
                    if (map.getMap().containsValue(creature)) {
                        creature.makeMove(map);
                    }
                });
    }
}
