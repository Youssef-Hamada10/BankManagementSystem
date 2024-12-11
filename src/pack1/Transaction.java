package pack1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {

    // attributes
    private static int transnum = 1;
    private final int Id;
    private double amount;
    // date and time
    private static final LocalDateTime now = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static String date = now.format(formatter);

    // constructor
    public Transaction(){
        this.Id = Transaction.transnum;
        Transaction.transnum++;
    }

    // getters and setters
    public int getId() {
        return Id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

}
