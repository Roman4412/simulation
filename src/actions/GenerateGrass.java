package actions;

import entities.Grass;
import world_map.WorldMap;


public class GenerateGrass extends Generating {
    public GenerateGrass(int minAmount, int requiredAmount) {
        super(minAmount, requiredAmount);
    }
    @Override
    public void execute(WorldMap map) {
        long grassAmount = map.getEntitiesAmount(Grass.class);
        if (grassAmount < minAmount) {
            InitGrass initGrass = new InitGrass(requiredAmount);
            initGrass.execute(map);
        }
    }
}