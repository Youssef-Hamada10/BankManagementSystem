package pack1;

public class TransferTransaction extends Transaction{

    // attribute
    private static final String transactionType = "TransferTransaction";
    private int senderAccount;
    private int recipientAccount;

    // getters and setters
    public static String getTransactionType() {
        return transactionType;
    }

    public int getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(int senderAccount) {
        this.senderAccount = senderAccount;
    }

    public int getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(int recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    //implement method
    public static void addTransactionToBank(double amount, int senderAccount, int recipientAccount){
        Transaction trans = new TransferTransaction() ;
        TransferTransaction transaction = (TransferTransaction) trans;
        transaction.setAmount(amount);
        transaction.setSenderAccount(senderAccount);
        transaction.setRecipientAccount(recipientAccount);
        Bank.transactions.add(transaction);

    }
    public static Transaction addTransactionToAccount(double amount, int senderAccount, int recipientAccount){
        Transaction trans = new TransferTransaction() ;
        TransferTransaction transaction = (TransferTransaction) trans;
        transaction.setAmount(amount);
        transaction.setSenderAccount(senderAccount);
        transaction.setRecipientAccount(recipientAccount);
        return transaction;

    }

}
