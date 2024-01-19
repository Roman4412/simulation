import Actions.Action;
import world_map.WorldMap;

import java.util.List;

public class Simulation {
    private long turn_counter = 0;
    private final WorldMap map;
    private final SimulationMapRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private boolean isRunning;

    public Simulation(WorldMap map, SimulationMapRenderer renderer, List<Action> initActions, List<Action> turnActions) {
        this.map = map;
        this.renderer = renderer;
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    public void startSimulation() {
        isRunning = true;
        while (isRunning) {
            nextTurn();
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void pauseSimulation() {
        isRunning = false;
    }

    public void nextTurn() {
        if (turn_counter == 0) {
            initActions.forEach(action -> action.execute(map));
            renderer.render(map,turn_counter);
            turn_counter++;
            return;
        }
        turnActions.forEach(action -> action.execute(map));
        renderer.render(map, turn_counter);
        turn_counter++;
    }

    public boolean isRunning() {
        return isRunning;
    }

}
