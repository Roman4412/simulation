import Actions.Action;
import world_map.WorldMap;

import java.util.List;

public class Simulation {
    private long turn_counter = 0;
    private final WorldMap map;
    private final SimulationMapRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private boolean state;

    public Simulation(WorldMap map, SimulationMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {
        state = true;
        while (state) {
            nextTurn();
        }
    }

    public void pauseSimulation() {
        state =  false;
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
