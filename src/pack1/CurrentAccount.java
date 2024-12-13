package pack1;

public class CurrentAccount extends Account {

    // attributes
    private final double minimumBalance;
    private final double fees;

    // constructor
    public CurrentAccount(double balance) {
        super(balance, "current account");
        minimumBalance = 3000;
        fees = 500;
    }

    // getters and setters
    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getFees() {
        return fees;
    }
}
