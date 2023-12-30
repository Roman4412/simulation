import Actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(20);
        SimulationMapRenderer renderer = new SimulationMapRenderer();
        List<Action> initAction = List.of(
                new InitHerbivores(10),
                new InitPredators(1),
                new InitGrass(0),
                new InitTrees(0),
                new InitRocks(0),
                new InitLand()
        );
        List<Action> turnAction = List.of(new AllMakeMove());

        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        simulation.startSimulation();

    }
}