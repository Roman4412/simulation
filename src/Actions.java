import entities.Herbivore;
import world_map.Position;
import world_map.WorldMap;

import java.util.Random;


public class Actions {
    Random random = new Random();
    public void setHerbivoresOnMap(WorldMap map,int amount) {
        int count = 0;
        while (count < 10) {
            Position position = map.getAllPositions()[random.nextInt(map.getAllPositions().length)];
            if (!map.getMap().containsKey(position)) {
                map.getMap().put(position, new Herbivore(position));
                count++;
            }
        }
        System.out.printf("Herbivores initialized %d/%d", count, amount);
    }

}
