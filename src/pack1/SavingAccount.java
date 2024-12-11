package pack1;

public class SavingAccount extends Account {

    // attributes
    private static double interestRate = 0.75;

    // getters and setters
    public double getInterestRate() {
        return interestRate;
    }

    // constructor
    public SavingAccount(double balance) {
        super(balance, "saving account");
    }

    // implement method
    public void applyInterest() {
       super.setBalance(super.getBalance() * SavingAccount.interestRate);
        System.out.println("Interest applied. Your current Balance is : " + super.getBalance());
    }


}
