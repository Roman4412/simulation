import Actions.Action;
import world_map.WorldMap;

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
    private final Action[] initActions;
    private final Action[] turnActions;

    public Simulation(WorldMap map, SimulationMapRenderer renderer, Action[] initActions, Action[] turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {
        while (true) {
            renderer.render(map);
        }
    }

    public void pauseSimulation() {

    }

    public void nextTurn() {

    }
}
