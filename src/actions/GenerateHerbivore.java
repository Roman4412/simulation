package actions;

import entities.Herbivore;
import world_map.WorldMap;

public class GenerateHerbivore extends Generating {
    private final int speed;
    public GenerateHerbivore(int minAmount, int requiredAmount,int speed) {
        super(minAmount, requiredAmount);
        this.speed = speed;
    }

    @Override
    public void execute(WorldMap map) {
        long herbivoresAmount = map.getEntitiesAmount(Herbivore.class);
        if (herbivoresAmount < minAmount) {
            InitHerbivores initHerbivores = new InitHerbivores(requiredAmount, speed);
            initHerbivores.execute(map);
        }
    }
}
