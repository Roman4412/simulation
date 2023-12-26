import entities.Entity;
import entities.Herbivore;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;
import java.util.Optional;

public class SimulationMapRenderer {
    private static final String PREDATOR = "\033[5;38;05;196m\u25A0";
    private static final String HERBIVORE = "\033[38;05;226m\u25A0";
    private static final String ROCK = "\033[38;05;242m\u25A0";
    private static final String GRASS = "\033[38;05;46m\u25A0";
    private static final String TREE = "\033[38;05;28m\u25A0";
    private static final String LAND = "-";
    private static final String RESET = "\033[0m";
    private static final String SEPARATOR = "  ";

    public void render(WorldMap map) {
        clearConsole();
        map.getMap().keySet().stream()
                .sorted((p1, p2) -> {
                    if (p1.v == p2.v) {
                        return p1.h - p2.h;
                    } else {
                        return p1.v - p2.v;
                    }
                }).forEach(p -> {
                    System.out.print(getSprite(map.getMap().get(p)) + SEPARATOR + RESET);
                    if (p.h == map.getSize()) {
                        System.out.println();
                    }
                });
        /*Optional<Position> first = map.getMap().values().stream()
                .filter(e -> e instanceof Herbivore)
                .map(h -> h.getPosition())
                .findFirst();
        System.out.println(first);*/
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

    private void clearConsole() {
        try {
            Thread.sleep(1000);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Ошибка при очистке консоли: " + e.getMessage());
        }

    }

}
