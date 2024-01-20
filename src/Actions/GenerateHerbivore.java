package Actions;

import entities.Herbivore;
import world_map.WorldMap;

public class GenerateHerbivore extends  Generating{
    public GenerateHerbivore(int minAmount, int requiredAmount) {
        super(minAmount, requiredAmount);
    }

    @Override
    public void execute(WorldMap map) {
        long herbivoresAmount = map.getEntitiesAmount(Herbivore.class);
        if (herbivoresAmount < minAmount) {
            InitHerbivores initHerbivores = new InitHerbivores(requiredAmount);
            initHerbivores.execute(map);
        }
    }
}
