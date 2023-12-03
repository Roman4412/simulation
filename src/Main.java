import world_map.Position;
import world_map.WorldMap;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(20);
        map.setHerbivoresOnMap(10);
        map.setTest(390);
        Renderer renderer = new Renderer();
        renderer.printMapState(map);
    }
}