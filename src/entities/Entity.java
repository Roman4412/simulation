package entities;

import world_map.Position;

import java.util.Objects;

public class Entity {
    Position position;

    public Entity(Position position) {
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(position, entity.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
