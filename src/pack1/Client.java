package pack1;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {

    // object from Scanner class
    Scanner input = new Scanner(System.in);

    // list of account
    ArrayList<Account> accounts = new ArrayList<>();

    // attributes
    private final int ID;

    // constructors
    public Client() {
        ID = User.clicount;
        User.clicount++;
    }

    // getters and setters
    public int getID() {
        return ID;
    }


    // implement methods
    public void editPersonalInfo() {
        System.out.println("Press [1] to edit password");
        System.out.println("Press [2] to edit address");
        System.out.println("Press [3] to edit telephone");
        System.out.println("Press [4] to exit");
        int number = input.nextInt();
        input.nextLine();
        switch (number){
            case 1:
                System.out.println("Enter the new password : ");
                setPassword(input.nextLine());
                break;
            case 2:
                System.out.println("Enter the new address : ");
                setAddress(input.nextLine());
                break;
            case 3:
                System.out.println("Enter the new telephone number : ");
                this.setTelephoneNumber(input.nextLine());
            case 4:
                return;
            default:
                System.out.println("Invalid insertion");
        }
    }

    public void displayDetails() {
        System.out.printf("Client ID: %d \n",this.getID());
        System.out.println("Name: " + this.getFirstName() + " " + this.getLastName());
        System.out.printf("Client address: %s \n",this.getAddress());
        System.out.printf("Client Telephone Number: %s \n",this.getTelephoneNumber());
        if(accounts.isEmpty()){
            System.out.println("You don't have any account yet");
        } else{
            int i = 0;
            for ( ;i < accounts.size(); i++){
                System.out.println("-----------------");
                System.out.printf("Account #%d \n",(i + 1));
                System.out.printf("Account number: %d \n",accounts.get(i).getAccountNumber());
                System.out.printf("Account status: %s \n",accounts.get(i).getAccountStatus());
                System.out.printf("Account type: %s \n",accounts.get(i).getAccountType());
                System.out.printf("Account balance: %f \n",accounts.get(i).getBalance());
                System.out.printf("Has a credit card: %b \n",accounts.get(i).getHasCreditCard());
            }
        }
    }

    int indexOfAccount;
    public void showAllAccount(){
        indexOfAccount = 0;
        for ( ; indexOfAccount < this.accounts.size(); indexOfAccount++){
            System.out.printf("[%d] Account number: %d   Status: %s   Balance: %f \n",(indexOfAccount + 1),this.accounts.get(indexOfAccount).getAccountNumber(),this.accounts.get(indexOfAccount).getAccountStatus(),this.accounts.get(indexOfAccount).getBalance());
        }
        System.out.println("Enter number of the account");
        indexOfAccount = (input.nextInt() - 1);
        input.nextLine();
    }

    public void clientPage(){
        System.out.println("---------------");
        System.out.printf("Welcome, %s %s \n",this.getFirstName(),this.getLastName());
        while (true){
            System.out.println("---------------");
            System.out.println("Press [1] to edit personal information");
            System.out.println("Press [2] to display the details of the account");
            System.out.println("Press [3] to transfer money to another account");
            System.out.println("Press [4] to make a deposit");
            System.out.println("Press [5] to withDraw");
            System.out.println("Press [6] to show transaction history");
            System.out.println("Press [7] to ask for credit card");
            System.out.println("Press [8] to pay with credit card");
            System.out.println("Press [9] to disable the credit card");
            System.out.println("Press [10] to exchange loyalty points");
            System.out.println("Press [11] to logout");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice){
                case 1:
                    editPersonalInfo();
                    break;
                case 2:
                    displayDetails();
                    break;
                case 3:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).transferMoney();
                    break;
                case 4:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).makeDeposit();
                    break;
                case 5:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).withDraw();
                    break;
                case 6:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).showTransactionHistory();
                    break;
                case 7:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).askForCreditCard();
                    break;
                case 8:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).payWithCreditCard();
                    break;
                case 9:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).disableCreditCard();
                    break;
                case 10:
                    showAllAccount();
                    this.accounts.get(indexOfAccount).exchangeLoyaltyPoints();
                    break;
                case 11:
                    return;
                default :
                    System.out.println("Invalid insertion");
                    System.out.println("ReEnter your choice");
            }
        }
    }
}
