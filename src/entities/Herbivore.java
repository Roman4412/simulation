package entities;

public class Herbivore extends Creature {
    public Herbivore(char sprite, int health, int speed) {
        super(sprite, health, speed);
    }

    @Override
    void makeMove() {

    }

    @Override
    public String toString() {
        return "Herbivore{" +
                "health=" + health +
                ", speed=" + speed +
                ", sprite=" + sprite +
                '}';
    }
}
