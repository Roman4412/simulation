import entities.Grass;
import entities.Herbivore;
import world_map.Position;
import world_map.WorldMap;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(10);
        //map.setEntities(0,100,100,0,0);
        map.setLand(100);

        Position herbPos = new Position(5, 5);
        Herbivore testHerb = new Herbivore(herbPos);
        Position grassPos = new Position(10, 10);
        Grass testGrass = new Grass(grassPos);

        map.setEntityToPos(herbPos, testHerb);
        //map.setEntityToPos(grassPos, testGrass);

        /*map.setHerbivore(1);
        map.setGrass(1);
        map.setLand(898);*/
        Renderer renderer = new Renderer();
        renderer.render(map);
        System.out.println(testHerb.findFood(map));
    }
}