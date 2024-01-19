import java.util.Scanner;

public class SimulationCommandHandler {
    Scanner scanner = new Scanner(System.in);
    Simulation simulation;

    public SimulationCommandHandler(Simulation simulation) {
        this.simulation = simulation;
    }

    public void startProcessing() {
        System.out.println("run");
        while (true) {
            if (scanner.hasNextLine()) {
                String in = scanner.nextLine().trim();
                switch (in) {
                    case "s":
                        if (simulation.isRunning()) {
                            System.out.println("already running");
                        } else {
                            Thread t = new Thread(() -> simulation.startSimulation());
                            t.start();
                        }
                        break;
                    case "p":
                        simulation.pauseSimulation();
                        break;
                    case "n":
                        if (simulation.isRunning()) {
                            //TODO кинуть эксепшн
                            System.out.println("already running");
                        } else {
                            simulation.nextTurn();
                        }
                        break;
                    default:
                        System.out.println("unknown command");
                }
                System.out.println(("run finish"));
            }
        }
    }
}