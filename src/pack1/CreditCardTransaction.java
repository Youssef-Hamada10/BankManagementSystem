package pack1;

public class CreditCardTransaction extends Transaction{
    //attribute
    private static String transactionType = "CreditCardTransaction";
    private int accountNumber;

    //getters and setters
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        CreditCardTransaction.transactionType = transactionType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    //implement method
    public static void addTransactionToBank(String name, double amount, int accountNumber){
//        Transaction trans = new CreditCardTransaction() ;
//        CreditCardTransaction transaction = (CreditCardTransaction) trans;
        CreditCardTransaction transaction = new CreditCardTransaction();
        transaction.setClientName(name);
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        Bank.transactions.add(transaction);
    }

}
