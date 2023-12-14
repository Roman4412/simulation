import entities.Entity;
import world_map.Position;
import world_map.WorldMap;

import java.util.List;
import java.util.stream.Collectors;

public class Renderer {
    private static final String PREDATOR = "\033[5;38;05;196m◼";
    private static final String HERBIVORE = "\033[38;05;226m◼";
    private static final String ROCK = "\033[38;05;242m◼";
    private static final String GRASS = "\033[38;05;46m◼";
    private static final String TREE = "\033[38;05;28m◼";
    private static final String LAND = "▢";
    private static final String RESET = "\033[0m";
    private static final String SEPARATOR ="  ";

    public void render(WorldMap map) {
        List<Position> positions = map.getMap().keySet().stream().sorted((a, b) -> {
            if (a.v == b.v) {
                return a.h - b.h;
            } else {
                return a.v - b.v;
            }
        }).collect(Collectors.toList());

        positions.forEach(p -> {
            System.out.print(getSprite(map.getEntityFromPosition(p)) + SEPARATOR + RESET);
            if (p.h == map.getSize()) {
                System.out.println();
            }
        });
    }



    private String getSprite(Entity entity) {
        switch (entity.getClass().getCanonicalName()) {
            case "entities.Predator":
                return PREDATOR;
            case "entities.Herbivore":
                return HERBIVORE;
            case "entities.Rock":
                return ROCK;
            case "entities.Grass":
                return GRASS;
            case "entities.Tree":
                return TREE;
            case "entities.Land":
                return LAND;
            default:
                return "?";
        }
    }
}
