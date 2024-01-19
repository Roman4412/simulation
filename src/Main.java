import Actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(30);
        SimulationMapRenderer renderer = new SimulationMapRenderer();
        List<Action> initAction = List.of(
                new InitHerbivores(10),
                new InitPredators(2),
                new InitGrass(30),
                new InitTrees(30),
                new InitRocks(30),
                new InitLand());
        List<Action> turnAction = List.of(new AllMakeMove(), new GenerateResources());
        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        SimulationCommandHandler handler = new SimulationCommandHandler(simulation);
        handler.startProcessing();
    }
}