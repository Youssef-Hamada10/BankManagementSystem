package pack1;

public class DepositTransaction extends Transaction{

    //attribute
    private static final String transactionType = "DepositTransaction";
    private int accountNumber;

    //getters and setters

    public static String getTransactionType() {
        return transactionType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    //implement method
    public static void addTransactionToBank(String name, double amount, int accountNumber){
//        Transaction trans = new DepositTransaction() ;
//        DepositTransaction transaction = (DepositTransaction) trans;
        DepositTransaction transaction = new DepositTransaction();
        transaction.setClientName(name);
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        Bank.transactions.add(transaction);
    }

}
