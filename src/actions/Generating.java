package actions;

public abstract class Generating implements Action {
    int minAmount;
    int requiredAmount;

    public Generating(int minAmount, int requiredAmount) {
        this.minAmount = minAmount;
        this.requiredAmount = requiredAmount;
    }
}
