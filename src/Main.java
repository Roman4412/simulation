import actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {
    private static final int RENDERER_DELAY_DURATION = 1000;
    private static final int MAP_SIZE = 30;
    private static final int INIT_HERBIVORES_AMOUNT = 15;
    private static final int INIT_PREDATORS_AMOUNT = 2;
    private static final int INIT_GRASS_AMOUNT = 25;
    private static final int INIT_TREES_AMOUNT = 45;
    private static final int INIT_ROCKS_AMOUNT = 30;
    private static final int DEFAULT_HERBIVORES_SPEED = 1;
    private static final int DEFAULT_PREDATORS_SPEED = 2;
    private static final int MIN_LIMIT_GRASS = 10;
    private static final int MIN_LIMIT_HERBIVORES = 5;
    private static final int GENERATED_GRASS_AMOUNT_PER_TURN = 15;
    private static final int GENERATED_HERBIVORES_AMOUNT_PER_TURN = 10;

    public static void main(String[] args) {
        WorldMap map = new WorldMap(MAP_SIZE);

        List<Action> initAction = List.of(
                new InitLand(),
                new InitHerbivores(INIT_HERBIVORES_AMOUNT, DEFAULT_HERBIVORES_SPEED),
                new InitPredators(INIT_PREDATORS_AMOUNT, DEFAULT_PREDATORS_SPEED),
                new InitGrass(INIT_GRASS_AMOUNT),
                new InitTrees(INIT_TREES_AMOUNT),
                new InitRocks(INIT_ROCKS_AMOUNT)
        );
        List<Action> turnAction = List.of(new AllMakeMove(),
                new GenerateGrass(MIN_LIMIT_GRASS, GENERATED_GRASS_AMOUNT_PER_TURN),
                new GenerateHerbivore(MIN_LIMIT_HERBIVORES,
                        GENERATED_HERBIVORES_AMOUNT_PER_TURN,
                        DEFAULT_HERBIVORES_SPEED)
        );

        SimulationMapRenderer renderer = new SimulationMapRenderer(RENDERER_DELAY_DURATION);
        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        SimulationCommandHandler handler = new SimulationCommandHandler(simulation);
        handler.startProcessing();
    }
}