import Actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(35);
        SimulationMapRenderer renderer = new SimulationMapRenderer();
        List<Action> initAction = List.of(
                new InitHerbivores(10),
                new InitPredators(3),
                new InitGrass(25),
                new InitTrees(15),
                new InitRocks(10),
                new InitLand()
        );
        List<Action> turnAction = List.of(new AllMakeMove());

        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        simulation.startSimulation();

    }
}