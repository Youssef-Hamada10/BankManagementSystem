package pack1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreditCard {

    // object from Scanner
    Scanner input = new Scanner(System.in);

    // attribute
    private final double limit;
    private double remainingLimit ;
    private final int accountNumber;
    private String status;
    private int loyaltyPoints = 0;
    // date and time
    private final LocalDateTime now = LocalDateTime.now();
    private final DateTimeFormatter fFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final LocalDateTime afterFourYears = LocalDateTime.now().plusYears(4);
    private final DateTimeFormatter sFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String date = now.format(fFormatter);
    private final String expDate = now.format(sFormatter);


    // constructor
    public CreditCard(int accountNumber) {
        this.accountNumber = accountNumber;
        this.status = "disabled";
        limit = 20000;
        remainingLimit = limit;
    }

    // getters and setters

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getRemainingLimit() {
        return remainingLimit;
    }

    public void setRemainingLimit(double remainingLimit) {
        this.remainingLimit = remainingLimit;
    }

    public double getLimit() {
        return limit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getDate() {
        return date;
    }

    public String getExpDate() {
        return expDate;
    }

}
