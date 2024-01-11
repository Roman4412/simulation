import entities.Entity;
import entities.Grass;
import entities.Herbivore;
import entities.Predator;
import world_map.WorldMap;


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
                    if (p1.vertical == p2.vertical) {
                        return p1.horizontal - p2.horizontal;
                    } else {
                        return p1.vertical - p2.vertical;
                    }
                }).forEach(p -> {
                    System.out.print(getSprite(map.getMap().get(p)) + SEPARATOR + RESET);
                    if (p.horizontal == map.getSize()) {
                        System.out.println();
                    }
                });
        System.out.println("Herbivores: " + map.getMap().values().stream().filter(h -> h instanceof Herbivore).count());
        System.out.println("Predators: " + map.getMap().values().stream().filter(h -> h instanceof Predator).count());
        System.out.println("Grass: " + map.getMap().values().stream().filter(h -> h instanceof Grass).count());
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
            Thread.sleep(800);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Ошибка при очистке консоли: " + e.getMessage());
        }

    }

}
