package pack1;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account {

    // object from Scanner class
    Scanner input = new Scanner(System.in);

    // list of transaction
    static ArrayList<Transaction> transactions = new ArrayList<>();

    //object from credit card
    CreditCard creditCard = new CreditCard(this.getAccountNumber());
    // attributes
    private static int accountnum = 11111;
    private final int accountNumber;
    private double balance;
    private String accountType;
    private String accountStatus;
    private boolean hasCreditCard;

    // constructor
    public Account(double balance, String accountType) {
        this.accountNumber = Account.accountnum;
        Account.accountnum++;
        this.accountStatus = "active";
        this.accountType = accountType;
        this.balance = balance;
    }

    // getters and setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean getHasCreditCard() {
        return hasCreditCard;
    }

    public void setHasCreditCard(boolean hasCreditCard) {
        this.hasCreditCard = hasCreditCard;
    }

    // implement method
    public void transferMoney() {
        // Transfer money to another account
        System.out.println("Enter the account number");
        int recipientAccount = input.nextInt();
        input.nextLine();
        int i = 0, j = 0;
        boolean found = false;
        for ( ; i < Bank.clients.size(); i++) {
            for( ; j < Bank.clients.get(i).accounts.size(); j++){
                if (Bank.clients.get(i).accounts.get(j).getAccountNumber() == recipientAccount) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (!found) {
            System.out.println("the recipient account not found ");
            return;
        }
        System.out.println("Enter amount to transfer :");
        double amount = input.nextDouble();
        input.nextLine();
        if (this.getBalance() < amount) {
            System.out.println("You don't hava enough money ");
            return;
        } else {
            Bank.clients.get(i).accounts.get(j).setBalance( Bank.clients.get(i).accounts.get(j).getBalance() + amount);
            this.setBalance(this.getBalance() - amount);
            System.out.printf("The remaining amount is : %f \n", this.getBalance());
        }
        // add to transaction list
        TransferTransaction.addTransactionToBank(amount, this.getAccountNumber(), recipientAccount);
        transactions.add(TransferTransaction.addTransactionToAccount(amount, this.getAccountNumber(), recipientAccount));

    }

    public void withDraw() {
        System.out.println("Enter the amount");
        double amount = input.nextDouble();
        input.nextLine();
        if (this.getBalance() < amount) {
            System.out.println("You don't have enough money");
            return;
        } else {
            this.setBalance(this.getBalance() - amount);
            System.out.printf("The remaining balance is : %f \n", this.getBalance());
        }
        // add to transaction list
//        WithDrawTransaction trans = new WithDrawTransaction();
        WithDrawTransaction.addTransactionToBank(amount, this.getAccountNumber());
        transactions.add(WithDrawTransaction.addTransactionToAccount(amount, this.getAccountNumber()));
    }

    public void makeDeposit() {
        // Make a deposit to the account
        System.out.println("Enter the amount :");
        double amount = input.nextDouble();
        input.nextLine();
        this.setBalance(this.getBalance() + amount);
        System.out.printf("Your current balance is : %f \n",this.getBalance());
        // add to transaction list
        DepositTransaction.addTransactionToBank(amount, this.getAccountNumber());
        transactions.add(DepositTransaction.addTransactionToAccount(amount, this.getAccountNumber()));
    }

    public void payWithCreditCard() {
        // Pay with the credit card
       if(this.getHasCreditCard() == false){
           System.out.println("You do not have a credit card");
       } else {
           if (this.creditCard.getStatus().equals("disable")) {
               System.out.println("Your card is disabled");
               return;
           } else {
               System.out.println("enter the amount due");
               double amount = input.nextDouble();
               input.nextLine();
               if (this.creditCard.getRemainingLimit() >= amount) {
                   this.creditCard.setRemainingLimit(this.creditCard.getRemainingLimit() - amount);
                   System.out.printf("The remaining balance is : %f \n", this.creditCard.getRemainingLimit());
                   this.creditCard.setLoyaltyPoints(this.creditCard.getLoyaltyPoints() + 5);    // handle loyalty points
               } else {
                   System.out.println("You don't have enough money");
                   return;
               }
               // add to transaction list
               CreditCardTransaction.addTransactionToBank(amount, this.getAccountNumber());
               Account.transactions.add(CreditCardTransaction.addTransactionToAccount(amount, this.getAccountNumber()));
           }
       }
    }

    public void disableCreditCard() {
        this.creditCard.setStatus("disabled");
        System.out.println("Your credit card is disabled");
    }

    public void exchangeLoyaltyPoints(){
        if (this.creditCard.getLoyaltyPoints() == 0){
            System.out.println("You don not have loyalty point");
        } else {
            System.out.printf("Your loyalty points is : %d \n",this.creditCard.getLoyaltyPoints());
            double amount = this.creditCard.getLoyaltyPoints() * 1.0;
            System.out.printf("Your balance will increase by : %f \n",amount);
            this.creditCard.setRemainingLimit(this.creditCard.getRemainingLimit() + amount);
        }
    }

    public void askForCreditCard() {
        if (this.getHasCreditCard()){
            System.out.println("You already have one");
        } else {
            this.setHasCreditCard(true);
            this.creditCard.setStatus("active");
            System.out.println("You now have a credit card");
        }
    }

    public void showTransactionHistory() {
        for (int i = 0; i < transactions.size(); i++){
            System.out.printf("ID: %d \n",transactions.get(i).getId());
            System.out.printf("Date: %s \n",transactions.get(i).getDate());
            System.out.printf("Amount: %f \n",transactions.get(i).getAmount());
            if (transactions.get(i) instanceof TransferTransaction){
                System.out.println("Transaction type: Transfer Transaction");
                System.out.printf("Sender account: %d \n",((TransferTransaction)transactions.get(i)).getSenderAccount());
                System.out.printf("Recipient account: %d \n",((TransferTransaction)transactions.get(i)).getRecipientAccount());
            } else if (transactions.get(i) instanceof WithDrawTransaction) {
                System.out.println("Transaction type: WithDraw Transaction");
                System.out.printf("Account number: %d \n",((WithDrawTransaction) transactions.get(i)).getAccountNumber());
            } else if (transactions.get(i) instanceof DepositTransaction){
                System.out.println("Transaction type: Deposit Transaction");
                System.out.printf("Account number: %d \n",((DepositTransaction) transactions.get(i)).getAccountNumber());
            } else {
                // credit card transaction
                System.out.println("Transaction type: Credit card Transaction");
                System.out.printf("Account number: %d \n",((CreditCardTransaction) transactions.get(i)).getAccountNumber());

            }
            System.out.println("----------------");
        }
    }

}
