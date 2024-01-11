import Actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(15);
        SimulationMapRenderer renderer = new SimulationMapRenderer();
        List<Action> initAction = List.of(
                new InitHerbivores(1),
                new InitPredators(0),
                new InitGrass(10),
                new InitTrees(15),
                new InitRocks(15),
                new InitLand());
        List<Action> turnAction = List.of(new AllMakeMove(), new GenerateResources());

        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        simulation.startSimulation();

    }
}