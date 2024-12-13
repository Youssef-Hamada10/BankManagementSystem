package pack1;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        System.out.println("-------------");
        boolean isTransfered = false;
        do {
            try {
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
                boolean valid = false;
                do {
                    try {
                        System.out.println("Enter amount to transfer :");
                        double amount = input.nextDouble();
                        input.nextLine();
                        valid = true;
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
                        isTransfered = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input.");
                        input.nextLine(); // Clear the input buffer
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (!valid);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!isTransfered);
    }

    public void withDraw() {
        System.out.println("---------------");
        double amount = 0;
        boolean valid = false;
        do {
            try {
                System.out.println("Enter the amount");
                amount = input.nextDouble();
                input.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        if (this.getBalance() < amount) {
            System.out.println("You don't have enough money");
            return;
        } else {
            this.setBalance(this.getBalance() - amount);
            System.out.printf("The remaining balance is : %f \n", this.getBalance());
        }
        // add to transaction list
        WithDrawTransaction.addTransactionToBank(amount, this.getAccountNumber());
        transactions.add(WithDrawTransaction.addTransactionToAccount(amount, this.getAccountNumber()));
    }

    public void makeDeposit() {
        System.out.println("---------------");
        double amount = 0;
        boolean valid = false;
        do {
            try {
                System.out.println("Enter the amount :");
                amount = input.nextDouble();
                input.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        this.setBalance(this.getBalance() + amount);
        System.out.printf("Your current balance is : %f \n",this.getBalance());
        // add to transaction list
        DepositTransaction.addTransactionToBank(amount, this.getAccountNumber());
        transactions.add(DepositTransaction.addTransactionToAccount(amount, this.getAccountNumber()));
    }

    public void payWithCreditCard() {
        System.out.println("-------------");
        double amount = 0;
       if(!this.getHasCreditCard()){
           System.out.println("You do not have a credit card");
       } else {
           if (this.creditCard.getStatus().equals("disable")) {
               System.out.println("Your card is disabled");
               return;
           } else {
               boolean valid = false;
               do {
                    try {
                        System.out.println("enter the amount due");
                        amount = input.nextDouble();
                        input.nextLine();
                        valid = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input.");
                        input.nextLine(); // Clear the input buffer
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (!valid);
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
        System.out.println("---------------");
        this.creditCard.setStatus("disabled");
        System.out.println("Your credit card is disabled");
    }

    public void exchangeLoyaltyPoints(){
        System.out.println("----------------");
        if (this.creditCard.getLoyaltyPoints() == 0){
            System.out.println("You don not have loyalty point");
        } else {
            System.out.printf("Your loyalty points is : %d \n",this.creditCard.getLoyaltyPoints());
            double amount = this.creditCard.getLoyaltyPoints() * 2.0;
            System.out.printf("Your balance will increase by : %f \n",amount);
            this.creditCard.setRemainingLimit(this.creditCard.getRemainingLimit() + amount);
        }
    }

    public void askForCreditCard() {
        System.out.println("--------------");
        if (this.getHasCreditCard()){
            System.out.println("You already have one");
        } else {
            this.setHasCreditCard(true);
            this.creditCard.setStatus("active");
            System.out.println("You now have a credit card with amount of 20000");
        }
    }

    public void showTransactionHistory() {
        System.out.println("---------------");
        int counter = 1;
        for (Transaction transaction : transactions) {
            System.out.printf("[%d]\t\tID: %d\tDate: %s\tAmount: %f\t",counter,transaction.getId(),transaction.getDate(),transaction.getAmount());
            if (transaction instanceof TransferTransaction) {
                System.out.print("Transaction type: Transfer Transaction\t");
                System.out.printf("Sender account: %d \t", ((TransferTransaction) transaction).getSenderAccount());
                System.out.printf("Recipient account: %d \n", ((TransferTransaction) transaction).getRecipientAccount());
            } else if (transaction instanceof WithDrawTransaction) {
                System.out.print("Transaction type: WithDraw Transaction \t");
                System.out.printf("Account number: %d \n", ((WithDrawTransaction) transaction).getAccountNumber());
            } else if (transaction instanceof DepositTransaction) {
                System.out.print("Transaction type: Deposit Transaction \t");
                System.out.printf("Account number: %d \n", ((DepositTransaction) transaction).getAccountNumber());
            } else {
                System.out.print("Transaction type: Credit card Transaction \t");
                System.out.printf("Account number: %d \n", ((CreditCardTransaction) transaction).getAccountNumber());
            }
            counter++;
        }
    }
}