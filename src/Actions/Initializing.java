package Actions;

import java.util.Random;

public abstract class Initializing implements Action {
    Random random = new Random();
    int amount;
    int counter = 0;

    public Initializing(int amount) {
        this.amount = amount;
    }

    public Initializing() {
    }
}
