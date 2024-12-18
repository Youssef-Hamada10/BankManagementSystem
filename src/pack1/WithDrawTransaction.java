package pack1;

public class WithDrawTransaction extends Transaction{

    //attribute
    private static String transactionType = "WithDrawTransaction";
    private int accountNumber;

    //getters and setters
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        WithDrawTransaction.transactionType = transactionType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    //implement method
    public static void addTransactionToBank(String name,double amount, int accountNumber){
//        Transaction trans = new WithDrawTransaction() ;
//        WithDrawTransaction transaction = (WithDrawTransaction) trans;
        WithDrawTransaction transaction = new WithDrawTransaction();
        transaction.setClientName(name);
        transaction.setAmount(amount);
        transaction.setAccountNumber(accountNumber);
        Bank.transactions.add(transaction);
    }

}
