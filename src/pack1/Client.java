package pack1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

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
        System.out.println("------------");
        boolean valid = false;
        do {
            try {
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
                        valid = true;
                        break;
                    case 2:
                        System.out.println("Enter the new address : ");
                        setAddress(input.nextLine());
                        valid = true;
                        break;
                    case 3:
                        System.out.println("Enter the new telephone number : ");
                        this.setTelephoneNumber(input.nextLine());
                        valid = true;
                        break;
                    case 4:
                        valid = true;
                        return;
                    default:
                        System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void displayDetails() {
        System.out.println("--------------");
        System.out.printf("Client ID: %d \n",this.getID());
        System.out.println("Name: " + this.getFirstName() + " " + this.getLastName());
        System.out.printf("Client address: %s \n",this.getAddress());
        System.out.printf("Client Telephone Number: %s \n",this.getTelephoneNumber());
        if(accounts.isEmpty()){
            System.out.println("You don't have any account yet");
        } else{
            int i = 0;
            for ( ;i < accounts.size(); i++){
                System.out.printf("[%d]\t\tAccount number: %d\tStatus: %s\tType: %s\tBalance: %f\t Has credit card: %b \n",(i + 1),accounts.get(i).getAccountNumber(),
                        accounts.get(i).getAccountStatus(),accounts.get(i).getAccountType(),accounts.get(i).getBalance(),accounts.get(i).getHasCreditCard());
            }
        }
    }

    int indexOfAccount;
    public void showAllAccount(){
        boolean found = false;
        do {
            try {
                indexOfAccount = 0;
                for ( ; indexOfAccount < this.accounts.size(); indexOfAccount++){
                    System.out.printf("[%d]\t\tAccount number: %d\tStatus: %s\tBalance: %f \n",(indexOfAccount + 1),
                            this.accounts.get(indexOfAccount).getAccountNumber(),this.accounts.get(indexOfAccount).getAccountStatus(),
                            this.accounts.get(indexOfAccount).getBalance());
                }
                System.out.println("Enter number of the account");
                indexOfAccount = (input.nextInt() - 1);
                input.nextLine();
                found = true;
            } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input.");
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
        } while (!found);
    }

    public void clientPage(){
        System.out.println("---------------");
        System.out.printf("Welcome, %s %s \n",this.getFirstName(),this.getLastName());
        boolean valid = false;
        while (true){
            try {
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
                        System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
        }
    }
}

