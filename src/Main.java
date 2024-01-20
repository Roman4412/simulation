import Actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(30);

        List<Action> initAction = List.of(
                new InitLand(),
                new InitHerbivores(10),
                new InitPredators(2),
                new InitGrass(30),
                new InitTrees(30),
                new InitRocks(30)
        );
        List<Action> turnAction = List.of(new AllMakeMove(),
                new GenerateGrass(10,20),
                new GenerateHerbivore(3,10)
        );

        SimulationMapRenderer renderer = new SimulationMapRenderer();
        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        SimulationCommandHandler handler = new SimulationCommandHandler(simulation);
        handler.startProcessing();
    }
}