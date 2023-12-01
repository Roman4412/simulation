package entities;

public abstract class Creature extends Entity {
    final int health;
    final int speed;

    public Creature(char sprite, int health, int speed) {
        super(sprite);
        this.health = health;
        this.speed = speed;
    }

    abstract void makeMove();
    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "health=" + health +
                ", speed=" + speed +
                ", sprite=" + sprite +
                '}';
    }
}
