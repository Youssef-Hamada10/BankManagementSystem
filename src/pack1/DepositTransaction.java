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
    public static void addTransactionToBank(double amount, int accountNumber){
        Transaction trans = new DepositTransaction() ;
        DepositTransaction transaction = (DepositTransaction) trans;
//        System.out.println(DepositTransaction.getTransactionType());
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        Bank.transactions.add(transaction);
    }
    public static Transaction addTransactionToAccount(double amount, int accountNumber){
        Transaction trans = new DepositTransaction() ;
        DepositTransaction transaction = (DepositTransaction) trans;
//        System.out.println(DepositTransaction.getTransactionType());
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        return transaction;
    }
}
