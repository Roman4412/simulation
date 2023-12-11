package entities;

import world_map.Position;

public class Entity {
    public Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
