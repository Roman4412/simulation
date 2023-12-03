import world_map.WorldMap;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(10);
        Actions actions = new Actions();
        actions.setHerbivoresOnMap(map,10);
    }
}