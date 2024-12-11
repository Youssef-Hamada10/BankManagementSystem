package pack1;

public class CurrentAccount extends Account {

    // attributes
    private final double minimumBalance = 3000;
    private final double fees = 500;



    // constructor
    public CurrentAccount(double balance) {
        super(balance, "current account");

    }

    // getters and setters
    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getFees() {
        return fees;
    }



}
