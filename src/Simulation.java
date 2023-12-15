import world_map.Renderer;
import world_map.WorldMap;

public class Simulation {
    /*
    init:
    setup creatures
    setup static obj / land, rock. tree, grass

    turn:
    creatures move
    generate grass
    delete grass/herb




     */
    private long turn_counter = 0;
    private final WorldMap map;
    private final Renderer renderer;
    private final Actions[] initActions;
    private final Actions[] turnActions;

    public Simulation(WorldMap map, Renderer renderer, Actions[] initActions, Actions[] turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {

    }

    public void pauseSimulation() {

    }

    public void nextTurn() {

    }
}
