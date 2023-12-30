package Actions;

import java.util.Random;

public abstract class InitAction {
    Random random = new Random();
    int amount;
    int counter = 0;

    public InitAction(int amount) {
        this.amount = amount;
    }

    public InitAction() {
    }
}
