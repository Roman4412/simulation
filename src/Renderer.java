import world_map.WorldMap;

public class Renderer {
    private static final String PREDATOR ="\033[5;38;05;196m◼";
    private static final String HERBIVORE ="\033[38;05;226m◼";
    private static final String ROCK = "\033[38;05;242m◼";
    private static final String GRASS ="\033[38;05;46m◼";
    private static final String TREE ="\033[38;05;28m◼";
    private static final String EMPTY ="▢";
    private static final String RESET ="\033[0m";

    public void printMapState(WorldMap map, int length, int width) {

    }
}
