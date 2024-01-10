package Actions;

import entities.Entity;
import entities.Grass;
import entities.Herbivore;
import entities.Land;
import world_map.Position;
import world_map.WorldMap;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateResources implements Action {

    @Override
    public void execute(WorldMap map) {

        Map<Position,Entity> copy = new HashMap<>(map.getMap());
        long herbivoresAmount = map.getMap().values().stream()
                .filter(value -> value instanceof Herbivore)
                .count();
        long grassAmount = map.getMap().values().stream()
                .filter(value -> value instanceof Grass)
                .count();
        if (grassAmount < 13) {
            generateGrass(map);
        }
    }

    private void generateGrass(WorldMap map) {
        List<Position> availablePos = new ArrayList<>();
        for (Entity e: map.getMap().values()) {
            if(e instanceof Land) {
                availablePos.add(e.getPosition());
            }
        }
        for (int i = 0; i < 5; i++) {
            Position randomPos = availablePos.get((int) (Math.random() * availablePos.size()));
            map.getMap().put(randomPos, new Grass(randomPos));
            availablePos.remove(randomPos);
        }

    }

    private void generateHerbivore(WorldMap map) {
        // ПОЧЕМУ ПРИ ИСПОЛЬЗОВАНИИ СТРИМОВ ПАДАЕТ АНСАПОРТЕД ОПЕРЭЙШН ЭКСЕПШН??
        List<Position> availablePos = map.getMap().values().stream()
                .filter(value -> value instanceof Land)
                .map(Entity::getPosition)
                .toList();
        List<Position> pos = new ArrayList<>(availablePos);
        for (int i = 0; i < 5; i++) {
            Position randomPos = pos.get((int) (Math.random() * availablePos.size()));
            map.getMap().put(randomPos, new Herbivore(randomPos));
            pos.remove(randomPos);
        }
    }
}
