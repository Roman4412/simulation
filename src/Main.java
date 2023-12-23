import Actions.AllMakeMove;
import entities.Grass;
import entities.Herbivore;
import world_map.Position;
import world_map.WorldMap;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(10);
        SimulationMapRenderer renderer = new SimulationMapRenderer();


        Position herbPos = new Position(1, 1);
        Herbivore testHerb = new Herbivore(herbPos);
        Position herbPos1 = new Position(5, 4);
        Herbivore testHerb1 = new Herbivore(herbPos);

        Position grassPos = new Position(10, 10);
        Grass testGrass = new Grass(grassPos);

        map.setEntityToPos(herbPos, testHerb);
        map.setEntityToPos(herbPos1, testHerb1);
        map.setEntityToPos(grassPos, testGrass);
        map.setLand(97);

        renderer.render(map);

        AllMakeMove a = new AllMakeMove();
        for (int i = 0; i < 14; i++) {
            a.execute(map);
            renderer.render(map);

        }
    }
}