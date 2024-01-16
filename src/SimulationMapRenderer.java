import entities.*;
import world_map.Position;
import world_map.WorldMap;

import java.util.Comparator;


public class SimulationMapRenderer {
    //TODO избавиться от if и дублирования
    // хранить количество сущностей в переменных?
    private static final String PREDATOR = "\033[5;38;05;196m\u25A0";
    private static final String HERBIVORE = "\033[38;05;226m\u25A0";
    private static final String ROCK = "\033[38;05;242m\u25A0";
    private static final String GRASS = "\033[38;05;46m\u25A0";
    private static final String TREE = "\033[38;05;28m\u25A0";
    private static final String LAND = " ";
    private static final String RESET = "\033[0m";
    private static final String SEPARATOR = "  ";

    public void render(WorldMap map) {
        clearConsole();
        map.getMap().keySet().stream()
                .sorted(Comparator.comparingInt(Position::getVertical).thenComparingInt(Position::getHorizontal))
                .forEach(p -> {
                    if (p.getHorizontal() == map.getSize() && p.getVertical() == 1) {
                        System.out.print("     " + HERBIVORE + " Herbivores: " + map.getMap().values().stream().filter(h -> h instanceof Herbivore).count());
                    }
                    if (p.getHorizontal() == map.getSize() && p.getVertical() == 2) {
                        System.out.print("     " + PREDATOR + " Predators: " + map.getMap().values().stream().filter(h -> h instanceof Predator).count());
                    }
                    if (p.getHorizontal() == map.getSize() && p.getVertical() == 3) {
                        System.out.print("     " + GRASS + " Grass: " + map.getMap().values().stream().filter(h -> h instanceof Grass).count());
                    }
                    if (p.getHorizontal() == map.getSize() && p.getVertical() == 4) {
                        System.out.print("     " + ROCK + " Rock: " + map.getMap().values().stream().filter(h -> h instanceof Rock).count());
                    }
                    if (p.getHorizontal() == map.getSize() && p.getVertical() == 5) {
                        System.out.print("     " + TREE + " Tree: " + map.getMap().values().stream().filter(h -> h instanceof Tree).count());
                    }
                    System.out.print(getSprite(map.getMap().get(p)) + SEPARATOR + RESET);
                    if (p.getHorizontal() == map.getSize()) {
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

    private void clearConsole() {
        try {
            Thread.sleep(1200);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Ошибка при очистке консоли: " + e.getMessage());
        }
    }

}
