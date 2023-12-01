package entities;

public class Entity {
    final char sprite;

    public Entity(char sprite) {
        this.sprite = sprite;
    }

    public char getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "sprite=" + sprite +
                '}';
    }
}
