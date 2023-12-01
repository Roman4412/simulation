package entities;

public class Predator extends Creature {
    final int attack;

    public Predator(char sprite, int health, int speed, int attack) {
        super(sprite, health, speed);
        this.attack = attack;
    }

    @Override
    void makeMove() {

    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        return "Predator{" +
                "attack=" + attack +
                ", health=" + health +
                ", speed=" + speed +
                ", sprite=" + sprite +
                '}';
    }
}
