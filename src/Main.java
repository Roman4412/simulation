import Actions.*;
import world_map.WorldMap;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(30);
        SimulationMapRenderer renderer = new SimulationMapRenderer();
        List<Action> initAction = List.of(
                new InitHerbivores(10),
                new InitGrass(20),
                new InitLand()
        );
        List<Action> turnAction = List.of(new AllMakeMove());

        Simulation simulation = new Simulation(map, renderer, initAction, turnAction);
        simulation.startSimulation();

    }
}