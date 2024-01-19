import entities.*;
import world_map.Position;
import world_map.WorldMap;

import java.util.Comparator;


public class SimulationMapRenderer {
    private static final String PREDATOR = "\033[5;38;05;196m\u25A0";
    private static final String HERBIVORE = "\033[38;05;226m\u25A0";
    private static final String ROCK = "\033[38;05;242m\u25A0";
    private static final String GRASS = "\033[38;05;46m\u25A0";
    private static final String TREE = "\033[38;05;28m\u25A0";
    private static final String LAND = " ";
    private static final String RESET = "\033[0m";
    private static final String SEPARATOR = "  ";


    public void render(WorldMap map, long counter) {
        clearConsole();
        map.getMap().keySet().stream()
                .sorted(Comparator.comparingInt(Position::getY).thenComparingInt(Position::getX))
                .forEach(p -> {
                    System.out.print(getSprite(map.getMap().get(p)) + SEPARATOR + RESET);
                    if (p.getX() == map.getSize()) {
                        printInfo(p.getY(), map, counter);
                        System.out.println();
                    }
                });
    }


    private String getSprite(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Predator":
                return PREDATOR;
            case "Herbivore":
                return HERBIVORE;
            case "Rock":
                return ROCK;
            case "Grass":
                return GRASS;
            case "Tree":
                return TREE;
            case "Land":
                return LAND;
            default:
                return "?";
        }
    }
    private void printInfo(int lineNum, WorldMap map, long counter) {
        switch (lineNum) {
            case 1:
                System.out.print("    Turn: " + counter);
                break;
            case 3:
                System.out.print("     " + HERBIVORE + " Herbivores: " + map.getEntitiesAmount(Herbivore.class));
                break;
            case 4:
                System.out.print("     " + PREDATOR + " Predators: " + map.getEntitiesAmount(Predator.class));
                break;
            case 5:
                System.out.print("     " + GRASS + " Grass: " + map.getEntitiesAmount(Grass.class));
                break;
            case 6:
                System.out.print("     " + ROCK + " Rocks: " + map.getEntitiesAmount(Rock.class));
                break;
            case 7:
                System.out.print("     " + TREE + " Trees: " + map.getEntitiesAmount(Tree.class));
                break;
            case 10:
                System.out.print("     Commands:");
                break;
            case 11:
                System.out.print("     s - start");
                break;
            case 12:
                System.out.print("     p - pause:");
                break;
            case 13:
                System.out.print("     n - next turn:");
                break;
        }
    }

    private void clearConsole() {
        try {
            Thread.sleep(1000);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
