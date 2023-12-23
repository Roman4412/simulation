package Actions;

import entities.Creature;
import world_map.WorldMap;

import java.util.List;
import java.util.stream.Collectors;

public class AllMakeMove {
    public void execute(WorldMap map) {
        List<Creature> creatures = map.getMap().values().stream()
                .filter(a -> a instanceof Creature)
                .map(a -> (Creature) a)
                .collect(Collectors.toList());

        for (Creature c : creatures) {
            c.makeMove(map);
        }
    }
}
