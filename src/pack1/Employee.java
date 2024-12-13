package pack1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Employee extends User {

    // object from scanner class
    Scanner input = new Scanner(System.in);

    // list of client
    ArrayList<Client> clients = new ArrayList<>();

    // attributes
    private final int ID;
    private String position;
    private String graduatedCollege;
    private int yearOfGraduation;
    private String totalGrade;

    public Employee() {
        ID = User.empcount;
        User.empcount++;
    }

    // getters and setters
    public int getID() {
        return ID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGraduatedCollege() {
        return graduatedCollege;
    }

    public void setGraduatedCollege(String graduatedCollege) {
        this.graduatedCollege = graduatedCollege;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(String totalGrade) {
        this.totalGrade = totalGrade;
    }

    // implement methods
    public void displayEmployeeInfo() {
        System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
        System.out.println("ID : " + this.getID());
        System.out.println("Username : " + this.getUsername());
        System.out.println("password : " + this.getPassword());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Position : " + this.getPosition());
        System.out.println("Telephone number : " + this.getTelephoneNumber());
        System.out.println("graduated from : " + this.getGraduatedCollege() + " in : " + this.getYearOfGraduation());
        System.out.println("Total grade : " + this.getTotalGrade());
    }

    public void editPersonalInfo() {    // just address - position
        System.out.println("---------------");
        System.out.println("Your address is : " + getAddress());
        System.out.println("Your position is : " + getPosition());

        boolean valid = false;   // for check choice
        do {
            try {
                System.out.println("---------------");
                System.out.println("Press [1] to edit your address");
                System.out.println("press [2] to edit your position");
                System.out.println("Press [3] to exit");
                int choice = input.nextInt();
                input.nextLine();
                if (choice == 1) {
                    System.out.println("Enter the new address");
                    this.setAddress(input.nextLine());
                    valid = true;
                } else if (choice == 2) {
                    System.out.println("Enter the new position");
                    this.setPosition(input.nextLine());
                    valid = true;
                } else if (choice == 3) {
                    valid = true;
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void createClient() {
        System.out.println("-----------");
        Client client = new Client();
        System.out.println("Enter client's first name :");
        client.setFirstName(input.nextLine().trim());
        System.out.println("Enter client's last name");
        client.setLastName(input.nextLine().trim());
        client.setUsername();
        System.out.println("Enter client's password :");
        client.setPassword(input.nextLine());
        System.out.println("Enter client's telephone number :");
        client.setTelephoneNumber(input.nextLine());
        System.out.println("Enter client's address :");
        client.setAddress(input.nextLine());
        Bank.clients.add(client);
        clients.add(client);
        Bank.clients.add(client);
        System.out.println("Client created");
    }

    public void createClientAccount() {
        boolean valid = false;   // for checking
        do {
            try {
                System.out.println("---------------");
                for (int i = 0; i < Bank.clients.size(); i++) {
                    System.out.printf("[%d] ID: %d   Name: %s %s \n", (i + 1), clients.get(i).getID(), clients.get(i).getFirstName(), clients.get(i).getLastName());
                }
                System.out.println("---------------");
                System.out.println("Enter number of client to create account");
                int num = input.nextInt();
                input.nextLine();
                System.out.println("Enter account type");
                String type = input.nextLine();
                System.out.println("Enter balance");
                double balance = input.nextDouble();
                input.nextLine();
                if (type.trim().equalsIgnoreCase("saving account")) {
                    SavingAccount account = new SavingAccount(balance);
                    Bank.clients.get(num - 1).accounts.add(account);
                    Bank.accounts.add(account);
                    System.out.println("Account created");
                } else if (type.trim().equalsIgnoreCase("current account")) {
                    CurrentAccount account = new CurrentAccount(balance);
                    Bank.clients.get(num - 1).accounts.add(account);
                    Bank.accounts.add(account);
                    System.out.println("Account created");
                } else {
                    System.out.println("Invalid input.");
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void editClientAccount() {
        System.out.println("-----------------");
        if (Bank.clients.isEmpty()) {
            System.out.println("There is no client yet");
            return;
        } else {
            for (int numOfClient = 0; numOfClient < Bank.clients.size(); numOfClient++) {
                System.out.printf("[%d]\t\tID: %d\tName: %s %s\n", (numOfClient + 1), Bank.clients.get(numOfClient).getID(), Bank.clients.get(numOfClient).getFirstName(), Bank.clients.get(numOfClient).getLastName());
                System.out.println("----------");
                if (Bank.clients.get(numOfClient).accounts.isEmpty()) {
                    System.out.println("This client has no accounts");
                } else {
                    for (int numOfAccount = 0; numOfAccount < Bank.clients.get(numOfClient).accounts.size(); numOfAccount++) {
                        System.out.printf("[%d]\t\tAccount number: %d\tStatus: %s\tType: %s\tBalance: %f \n", (numOfAccount + 1),
                                Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountNumber(), Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountStatus(),
                                Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountType(), Bank.clients.get(numOfClient).accounts.get(numOfAccount).getBalance());
                    }
                }
                System.out.println("------------");
            }
            boolean valid = false;
            do {
                try {
                    System.out.println("Enter number of client");
                    int numOfClient = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter number of account to edit :");
                    int numOfAccount = input.nextInt();
                    input.nextLine();

                    boolean innerValid = false;
                    do {
                        try {
                            System.out.println("Press [1] to edit account status");
                            System.out.println("Press [2] to edit account balance");
                            System.out.println("Press [3] to exit");
                            int num = input.nextInt();
                            input.nextLine();
                            switch (num) {
                                case 1:
                                    System.out.println("Enter the new account status");
                                    Bank.clients.get(numOfClient - 1).accounts.get(numOfAccount - 1).setAccountStatus(input.nextLine());
                                    break;
                                case 2:
                                    System.out.println("Enter the new balance");
                                    Bank.clients.get(numOfClient - 1).accounts.get(numOfAccount - 1).setBalance(input.nextDouble());
                                    input.nextLine();
                                    break;
                                case 3:
                                    return;
                                default:
                                    System.out.println("Invalid input.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input.");
                            input.nextLine(); // Clear the input buffer
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid input.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } while (!innerValid);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine(); // Clear the input buffer
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid input.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!valid);
        }
    }

    public void searchForClient() {
        boolean found = false;
        do {
            try {
                System.out.println("-------------");
                System.out.println("[1] Using client's name");
                System.out.println("[2] Using account number");
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter client name :");
                        searchForClientByName(input.nextLine());
                        found = true;
                        break;
                    case 2:
                        System.out.println("Enter account number");
                        searchForClientByAccountNumber(input.nextInt());
                        found = true;
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!found);
    }

    public void searchForClientByName(String Name) {
        System.out.println("---------------");
        boolean found;
        boolean getclient = false;
        for (int i = 0; i < Bank.clients.size(); i++) {
            found = false;
            if (Bank.clients.get(i).getFirstName().concat(" " + Bank.clients.get(i).getLastName()).equalsIgnoreCase(Name.trim())) {
                getclient = true;
                found = true;
                if (found) {
                    System.out.println("The client is exist");
                    System.out.printf("Client name: %s %s\tAddress: %s\tTelephone number: %s \n", Bank.clients.get(i).getFirstName(), Bank.clients.get(i).getLastName(),
                            Bank.clients.get(i).getAddress(), Bank.clients.get(i).getTelephoneNumber());
                    System.out.println("------------");
                    if (Bank.clients.get(i).accounts.isEmpty()) {
                        System.out.printf("%s %s has no account \n", Bank.clients.get(i).getFirstName(), Bank.clients.get(i).getLastName());
                    } else {
                        System.out.printf("%s %s has %d account \n", Bank.clients.get(i).getFirstName(), Bank.clients.get(i).getLastName(), Bank.clients.get(i).accounts.size());
                        for (int j = 0; j < Bank.clients.get(i).accounts.size(); j++) {
                            System.out.printf("[%d]\t\tAccount number: %d\tStatus: %s\tType: %s\tBalance: %f\tHas credit card : %b \n",
                                    (j + 1), Bank.clients.get(i).accounts.get(j).getAccountNumber(), Bank.clients.get(i).accounts.get(j).getAccountStatus(),
                                    Bank.clients.get(i).accounts.get(j).getAccountType(), Bank.clients.get(i).accounts.get(j).getBalance(),
                                    Bank.clients.get(i).accounts.get(j).getHasCreditCard());
                        }
                    }
                }
            }
            if (!getclient) {
                System.out.println("No clients match your search");
            }
        }
    }

    public void searchForClientByAccountNumber(int accountNumber) {
        System.out.println("---------------");
        input.nextLine();
        boolean found = false;
        for (int numOfClient = 0; numOfClient < Bank.clients.size(); numOfClient++) {
            for (int numOfAccount = 0; numOfAccount < Bank.clients.get(numOfClient).accounts.size(); numOfAccount++) {
                if (Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountNumber() == accountNumber) {
                    found = true;
                    if (found) {
                        System.out.println("The client is exist");
                        System.out.printf("Name: %s %s\tAddress: %s\tTelephone number: %s\tStatus: %s\tType: %s\tBalance: %s\t Has credit card: %b", Bank.clients.get(numOfClient).getFirstName(),
                                Bank.clients.get(numOfClient).getLastName(), Bank.clients.get(numOfClient).getAddress(), Bank.clients.get(numOfClient).getTelephoneNumber(),
                                Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountStatus(), Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountType(),
                                Bank.clients.get(numOfClient).accounts.get(numOfAccount).getBalance(), Bank.clients.get(numOfClient).accounts.get(numOfAccount).getHasCreditCard());
                        break;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("The client is not exist ");
        }
    }

    public void deleteClient() {
        boolean valid = false;
        do {
            try {
                System.out.println("-------------");
                if (Bank.clients.isEmpty()) {
                    System.out.println("There is no client yet");
                } else {
                    for (int numOfClient = 0; numOfClient < Bank.clients.size(); numOfClient++) {
                        System.out.printf("[%d]\t\tID: %d\tName: %s %s\tNumber of accounts:%d \n", (numOfClient + 1), Bank.clients.get(numOfClient).getID(),
                                Bank.clients.get(numOfClient).getFirstName(), Bank.clients.get(numOfClient).getLastName(), Bank.clients.get(numOfClient).accounts.size());
                    }
                    System.out.println("Enter number of client to delete :");
                    int numOfClient = input.nextInt();
                    input.nextLine();
                    Bank.clients.remove(numOfClient - 1);
                    System.out.println("Client deleted");
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
    }

    public void deleteClientAccount () {
        System.out.println("--------------");
        for (int numOfClient = 0; numOfClient < Bank.clients.size(); numOfClient++){
            System.out.printf("[%d]\t\tID: %d\tName: %s %s\n", (numOfClient + 1), Bank.clients.get(numOfClient).getID(), Bank.clients.get(numOfClient).getFirstName(), Bank.clients.get(numOfClient).getLastName());
            System.out.println("-------------");
            if(Bank.clients.get(numOfClient).accounts.isEmpty()){
                System.out.println("This client has no account");
                System.out.println("--------------");
            } else {
                for (int numOfAccount = 0; numOfAccount < Bank.clients.get(numOfClient).accounts.size(); numOfAccount++){
                    System.out.printf("[%d]\t\tAccount number: %d\tStatus: %s\tType: %s\tBalance: %f \n", (numOfAccount + 1),
                            Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountNumber(), Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountStatus(),
                            Bank.clients.get(numOfClient).accounts.get(numOfAccount).getAccountType(), Bank.clients.get(numOfClient).accounts.get(numOfAccount).getBalance());
                }
            }
            System.out.println("-------------");
        }
        boolean valid = false;
        do {
            try {
                System.out.println("Enter client number");
                int numOfClient = input.nextInt();
                input.nextLine();
                System.out.println("Enter account number");
                int numOfAccount = input.nextInt();
                input.nextLine();
                Bank.clients.get(numOfClient - 1).accounts.remove(numOfAccount - 1);
                System.out.println("Account removed");
                valid = true;
            } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input.");
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
        } while (!valid);
    }


    public void employeePage () {
        System.out.println("---------------");
        System.out.printf("Welcome, %s %s \n",this.getFirstName(),this.getLastName());
        while (true) {
            try {
                System.out.println("---------------");
                System.out.println("Press [1] to edit personal info :");
                System.out.println("Press [2] to add new client :");
                System.out.println("Press [3] to create client account :");
                System.out.println("Press [4] to edit client account :");
                System.out.println("Press [5] to search for client :");
                System.out.println("Press [6] to delete client account :");
                System.out.println("Press [7] to delete client :");
                System.out.println("Press [8] to logout :");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        editPersonalInfo();
                        break;
                    case 2:
                        createClient();
                        break;
                    case 3:
                        createClientAccount();
                        break;
                    case 4:
                        editClientAccount();
                        break;
                    case 5:
                        searchForClient();
                        break;
                    case 6:
                        deleteClientAccount();
                        break;
                    case 7:
                        deleteClient();
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("Invalid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
