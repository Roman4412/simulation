import java.util.Scanner;

public class SimulationCommandHandler {
    private static final String START_COMMAND = "s";
    private static final String PAUSE_COMMAND = "p";
    private static final String NEXT_TURN_COMMAND = "n";
    private static final String COMMAND_FAIL_MESSAGE = "already running";
    private static final String UNKNOWN_COMMAND_MESSAGE = "unknown command";
    private static final String START_MESSAGE = "print \"s\" and press ENTER to start";
    private final Scanner scanner = new Scanner(System.in);
    private final Simulation simulation;

    public SimulationCommandHandler(Simulation simulation) {
        this.simulation = simulation;
    }

    public void startProcessing() {
        System.out.println(START_MESSAGE);
        while (true) {
            if (scanner.hasNextLine()) {
                String in = scanner.nextLine().trim();
                switch (in) {
                    case START_COMMAND:
                        if (simulation.isRunning()) {
                            System.out.println(COMMAND_FAIL_MESSAGE);
                        } else {
                            Thread t = new Thread(simulation::startSimulation);
                            t.start();
                        }
                        break;
                    case PAUSE_COMMAND:
                        simulation.pauseSimulation();
                        break;
                    case NEXT_TURN_COMMAND:
                        if (simulation.isRunning()) {
                            System.out.println(COMMAND_FAIL_MESSAGE);
                        } else {
                            simulation.nextTurn();
                        }
                        break;
                    default:
                        System.out.println(UNKNOWN_COMMAND_MESSAGE);
                }
            }
        }
    }
}