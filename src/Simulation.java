import Actions.Action;
import world_map.WorldMap;

import java.util.List;

public class Simulation {
    /*
    init:
    create map
    generate and setup:
        generateHerbivores
        generatePredators
        generateRocks
        generateTrees
        generateGrass
        generateLand


    turn:
    allMakeMove()
    checkResources() {
       generateGrass() if < 10
       generateHerbivores() if < 10
    }




     */
    private long turn_counter = 0;
    private final WorldMap map;
    private final SimulationMapRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(WorldMap map, SimulationMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {

        while (true) {
            nextTurn();
        }
    }

    public void pauseSimulation() {

    }

    public void nextTurn() {
        if (turn_counter == 0) {
            initActions.forEach(action -> action.execute(map));
            renderer.render(map);
        }
        turnActions.forEach(action -> action.execute(map));
        renderer.render(map);
        turn_counter++;
    }
}
