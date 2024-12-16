package pack1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {

    // attributes
    private String clientName;
    private static int transnum = 1;
    private final int Id;
    private double amount;
    private String transactionDate;


    // constructor
    public Transaction(){
        this.Id = Transaction.transnum;
        Transaction.transnum++;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = now.format(formatter);
        transactionDate = date;
    }

    // getters and setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

    public int getId() {
        return Id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

}
