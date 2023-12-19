import entities.Grass;
import entities.Herbivore;
import entities.Tree;
import world_map.Position;
import world_map.Renderer;
import world_map.WorldMap;


public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(10);


        /*map.setEntities(0,100,100,0,0);
        map.setLand(100);
        map.setEntities(15, 20, 20, 15, 30);
*/
        Position herbPos = new Position(5, 5);
        Herbivore testHerb = new Herbivore(herbPos);
        Position grassPos = new Position(4, 7);
        Position treePos = new Position(5, 6);
        Position treePos1 = new Position(4, 5);
        Grass testGrass = new Grass(grassPos);
        Tree testTree = new Tree(treePos);
        Tree testTree1 = new Tree(treePos1);

        /*Position p1= new Position(5,6);// -1.2 шагов до цели 3
        Position p2= new Position(5,4);// -1,4 шагов до цели 5
        Position p3= new Position(4,5);//  0.3 шагов до цели 3
        Position p4= new Position(6,5);// -2.3 шагов до цели 5
        Position p5= new Position(4,4);//  0.4 шагов до цели 4
        Position p6= new Position(4,6);//  0.2 шагов до цели 2
        Position p7= new Position(6,6);// -2.2 шагов до цели 4
        Position p8= new Position(6,4);// -2.4 шагов до цели 6



        map.setEntityToPos(p1, new Tree(p1));
        map.setEntityToPos(p2, new Tree(p2));
        map.setEntityToPos(p3, new Tree(p3));
        map.setEntityToPos(p4, new Tree(p4));
        map.setEntityToPos(p5, new Tree(p5));
        map.setEntityToPos(p6, new Tree(p6));
        map.setEntityToPos(p7, new Tree(p7));
        map.setEntityToPos(p8, new Tree(p8));*/

        //map.setEntityToPos(new Position(),new Tree());



        map.setEntityToPos(herbPos, testHerb);
        map.setEntityToPos(grassPos, testGrass);
        map.setEntityToPos(treePos, testTree);
        map.setEntityToPos(treePos1, testTree1);

        map.setLand(96);
        Renderer renderer = new Renderer();
        renderer.render(map);
        System.out.println();
        System.out.println();
        testHerb.makeMove(map);
        renderer.render(map);

        testHerb.makeMove(map);
        renderer.render(map);
        testHerb.makeMove(map);
        renderer.render(map);
        testHerb.makeMove(map);
        renderer.render(map);
        testHerb.makeMove(map);
        renderer.render(map);



    }
}