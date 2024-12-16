package pack1;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Admin {

    // object from Scanner class
    private static Scanner input = new Scanner(System.in);

    // attribute
    private final String username = "admin";
    private final String password = "admin";

    // getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // implement methods
    private static void addNewEmployee(){
        System.out.println("--------------");
        Employee emp = new Employee();
        System.out.println("Enter employee's first name :");
        emp.setFirstName(input.nextLine().trim());
        System.out.println("Enter employee's last name");
        emp.setLastName(input.nextLine().trim());
//        System.out.println("Enter employee's username :");
        emp.setUsername();
        System.out.println("Enter employee's password :");
        emp.setPassword(input.nextLine());
        System.out.println("Enter employee's telephone number :");
        emp.setTelephoneNumber(input.nextLine());
        System.out.println("Enter employee's address :");
        emp.setAddress(input.nextLine());
        System.out.println("Enter employee's position :");
        emp.setPosition(input.nextLine());
        System.out.println("Enter employee's graduated college :");
        emp.setGraduatedCollege(input.nextLine());

        boolean valid = false;   // for check graduation year
        do{
            try {
                System.out.println("Enter employee's year of graduation :");
                emp.setYearOfGraduation(input.nextInt());
                input.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            }
        } while(!valid);

        System.out.println("Enter employee's total grade :");
        emp.setTotalGrade(input.nextLine());
        System.out.println("Employee added");
        Bank.employees.add(emp);
    }

    private static void displayAllEmployees(){
        System.out.println("--------------");
        if (Bank.employees.isEmpty()){
            System.out.println("there is no employees yet");
            return;
        } else {
            for(int i = 0; i < Bank.employees.size(); i++){
                System.out.printf("[%d]\t\tID: %d\tName: %s %s \n",(i + 1),Bank.employees.get(i).getID(),Bank.employees.get(i).getFirstName(),Bank.employees.get(i).getLastName());
            }
            System.out.println("-------------");

            boolean valid = false;   // for check choice
            do{
                try {
                    System.out.println("Enter number of the employee to show his details");
                    int numOfEmployee = input.nextInt();
                    input.nextLine();
                    System.out.println("-------------");
                    System.out.printf("ID: %d \n",Bank.employees.get(numOfEmployee - 1).getID());
                    System.out.printf("Name: %s %s \n",Bank.employees.get(numOfEmployee - 1).getFirstName(),Bank.employees.get(numOfEmployee - 1).getLastName());
                    System.out.printf("Address: %s \n",Bank.employees.get(numOfEmployee - 1).getAddress());
                    System.out.printf("Position: %s \n",Bank.employees.get(numOfEmployee - 1).getPosition());
                    System.out.printf("Telephone number: %s \n",Bank.employees.get(numOfEmployee - 1).getTelephoneNumber());
                    System.out.printf("graduated from: %s \n",Bank.employees.get(numOfEmployee - 1).getGraduatedCollege());
                    System.out.printf("graduation year: %d \n",Bank.employees.get(numOfEmployee - 1).getYearOfGraduation());
                    System.out.printf("Total grade: %s \n",Bank.employees.get(numOfEmployee - 1).getTotalGrade());
                    valid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine(); // Clear the input buffer
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Invalid input.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while(!valid);
        }
    }

    private static void displayAllClients(){
        System.out.println("--------------");
        if (Bank.clients.isEmpty()){
            System.out.println("There is no clients yet");
            return;
        } else{
            for(int i = 0; i < Bank.clients.size(); i++){
                System.out.printf("[%d]\t\tID: %d\tName: %s %s \n",(i + 1),Bank.clients.get(i).getID(),Bank.clients.get(i).getFirstName(),Bank.clients.get(i).getLastName());
            }
            System.out.println("--------------");

            boolean valid = false;   // for check choice
            do {
                try {
                    System.out.println("Enter number of the client to show his details");
                    int numOfClient = input.nextInt();
                    input.nextLine();
                    System.out.printf("ID: %d \n",Bank.clients.get(numOfClient - 1).getID());
                    System.out.printf("Name: %s %s \n",Bank.clients.get(numOfClient - 1).getFirstName(),Bank.clients.get(numOfClient - 1).getLastName());
                    System.out.printf("Address: %s \n",Bank.clients.get(numOfClient - 1).getAddress());
                    System.out.printf("Telephone number: %s \n",Bank.clients.get(numOfClient - 1).getTelephoneNumber());
                    int numOfAccount = 0;
                    for (int i = 0; i < Bank.accounts.size(); i++) {
                        if (Bank.accounts.get(i).getClientName().equals(Bank.clients.get(numOfClient - 1).getFirstName().concat(" " + Bank.clients.get(numOfClient - 1).getLastName()))){
                            numOfAccount++;
                        }
                    }
                    if (numOfAccount == 0){
                        System.out.println("this client has no accounts");
                    } else {
                        System.out.printf("this client has %d account \n",numOfAccount);
                    }
                    valid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine(); // Clear the input buffer
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Invalid input.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while(!valid);

        }
    }

    private static void displayAllTransaction(){
        System.out.println("--------------");
        if (Bank.transactions.isEmpty()){
            System.out.println("There is no transactions yet");
            return;
        } else {
            boolean valid = false;   // for check choice
            do{
                System.out.println("Press [1] to display by date :");
                System.out.println("Press [2] to display by client :");
                System.out.println("Press [3] to display by employee :");
                System.out.println("Press [4] to exit :");
                try {
                    int choice = input.nextInt();
                    input.nextLine();
                    switch (choice){
                        case 1:
                            System.out.println("Enter date at the form dd-MM-yyyy :");
                            String date = input.nextLine();
                            displayTransactionsByDate(date.trim());
                            valid = true;
                            break;
                        case 2:
                            System.out.println("Enter client name :");
                            String clientName = input.nextLine();
                            displayTransactionsByClient(clientName.trim().toLowerCase());
                            valid = true;
                            break;
                        case 3:
                            System.out.println("Enter employee name :");
                            String employeeName = input.nextLine().trim().toLowerCase();
                            displayTransactionsByEmployee(employeeName);
                            valid = true;
                            break;
                        case 4:
                            valid = true;
                            return;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine(); // Clear the input buffer
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while(!valid);
        }
    }

    private static void displayTransactionsByDate(String date){
        System.out.println("--------------");
        if(Bank.transactions.isEmpty()){
            System.out.println("There is no transaction yet");
            return;
        } else{
            try {
                int counter = 0;    // to know the transaction is exist or no
                for(int i = 0; i < Bank.transactions.size(); i++){
                    if(Bank.transactions.get(i).getTransactionDate().contains(date)){
                        System.out.printf("Transaction id: %d\t",Bank.transactions.get(i).getId());
                        System.out.printf("Date: %s\t",Bank.transactions.get(i).getTransactionDate());
                        System.out.printf("Amount: %f\t",Bank.transactions.get(i).getAmount());
                        if(Bank.transactions.get(i) instanceof WithDrawTransaction){
                            System.out.printf("Transaction type: %s\t",WithDrawTransaction.getTransactionType());
                            System.out.printf("Account number: %d\n",((WithDrawTransaction) Bank.transactions.get(i)).getAccountNumber());
                        } else if (Bank.transactions.get(i) instanceof TransferTransaction) {
                            System.out.printf("Transaction type: %s\t",TransferTransaction.getTransactionType());
                            System.out.printf("Sender Account number: %d\t",((TransferTransaction) Bank.transactions.get(i)).getSenderAccount());
                            System.out.printf("Recipient Account number: %d\n",((TransferTransaction) Bank.transactions.get(i)).getRecipientAccount());
                        } else if (Bank.transactions.get(i) instanceof DepositTransaction) {
                            System.out.printf("Transaction type: %s\t",DepositTransaction.getTransactionType());
                            System.out.printf("Account number: %d\n",((DepositTransaction) Bank.transactions.get(i)).getAccountNumber());
                        } else {
                            System.out.printf("Transaction type: %s\n",CreditCardTransaction.getTransactionType());
                        }
                        counter++;
                    }
                }
                if(counter == 0){
                    System.out.println("There is no transaction with this date " + date);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e){
                System.out.println("Invalid input.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayTransactionsByClient(String clientName){
        System.out.println("--------------");
        if(Bank.clients.isEmpty()){
            System.out.println("There is no clients yet");
        } else if (Bank.transactions.isEmpty()) {
            System.out.println("There is no transaction yet");
        } else {
            try {
                int counter = 0;
                for(int i = 0; i < Bank.transactions.size(); i++){
                    if(Bank.transactions.get(i).getClientName().equals(clientName)){
                        System.out.printf("Transaction id: %d\t",Bank.transactions.get(i).getId());
                        System.out.printf("Date: %s\t",Bank.transactions.get(i).getTransactionDate());
                        System.out.printf("Amount: %f\t",Bank.transactions.get(i).getAmount());
                        if(Bank.transactions.get(i) instanceof WithDrawTransaction){
                            System.out.printf("Transaction type: %s\t",WithDrawTransaction.getTransactionType());
                            System.out.printf("Account number: %d\n",((WithDrawTransaction) Bank.transactions.get(i)).getAccountNumber());
                        } else if (Bank.transactions.get(i) instanceof TransferTransaction) {
                            System.out.printf("Transaction type: %s\t",TransferTransaction.getTransactionType());
                            System.out.printf("Sender Account number: %d\t",((TransferTransaction) Bank.transactions.get(i)).getSenderAccount());
                            System.out.printf("Recipient Account number: %d\n",((TransferTransaction) Bank.transactions.get(i)).getRecipientAccount());
                        } else if (Bank.transactions.get(i) instanceof DepositTransaction) {
                            System.out.printf("Transaction type: %s\t",DepositTransaction.getTransactionType());
                            System.out.printf("Account number: %d\n",((DepositTransaction) Bank.transactions.get(i)).getAccountNumber());
                        } else {
                            System.out.printf("Transaction type: %s\n",CreditCardTransaction.getTransactionType());
                        }
                        counter++;
                    }
                }
                if(counter == 0){
                    System.out.println("There is no transaction with this client " + clientName);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e){
                System.out.println("Invalid input.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void displayTransactionsByEmployee(String employeeName){
        System.out.println("--------------");
        if(Bank.employees.isEmpty()){
            System.out.println("There is no employees yet");
        } else if (Bank.transactions.isEmpty()) {
            System.out.println("There is no transactions yet");
        }else if (Bank.clients.isEmpty()) {
            System.out.println("There is no clients yet");
        } else {
            String clientName = null;     // to select the client
            try {
                int counter = 0;
                for (int k = 0; k < Bank.clients.size(); k++){
                    if (Bank.clients.get(k).getCreatedBy().equals(employeeName)){
                        clientName = Bank.clients.get(k).getFirstName().concat(" " + Bank.clients.get(k).getLastName());
                        for(int i = 0; i < Bank.transactions.size(); i++){
                            if(Bank.transactions.get(i).getClientName().equals(clientName)){
                                System.out.printf("Transaction id: %d\t",Bank.transactions.get(i).getId());
                                System.out.printf("Date: %s\t",Bank.transactions.get(i).getTransactionDate());
                                System.out.printf("Amount: %f\t",Bank.transactions.get(i).getAmount());
                                if(Bank.transactions.get(i) instanceof WithDrawTransaction){
                                    System.out.printf("Transaction type: %s\t",WithDrawTransaction.getTransactionType());
                                    System.out.printf("Account number: %d\n",((WithDrawTransaction) Bank.transactions.get(i)).getAccountNumber());
                                } else if (Bank.transactions.get(i) instanceof TransferTransaction) {
                                    System.out.printf("Transaction type: %s\t",TransferTransaction.getTransactionType());
                                    System.out.printf("Sender Account number: %d\t",((TransferTransaction) Bank.transactions.get(i)).getSenderAccount());
                                    System.out.printf("Recipient Account number: %d\n",((TransferTransaction) Bank.transactions.get(i)).getRecipientAccount());
                                } else if (Bank.transactions.get(i) instanceof DepositTransaction) {
                                    System.out.printf("Transaction type: %s\t",DepositTransaction.getTransactionType());
                                    System.out.printf("Account number: %d\n",((DepositTransaction) Bank.transactions.get(i)).getAccountNumber());
                                } else {
                                    System.out.printf("Transaction type: %s\n",CreditCardTransaction.getTransactionType());
                                }
                                counter++;
                            }
                        }
                    }
                }
                if(counter == 0){
                    System.out.println("There is no transaction with this employee " + employeeName);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear the input buffer
            } catch (IndexOutOfBoundsException e){
                System.out.println("Invalid input.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void adminPage(){
        System.out.println("---------------");
        System.out.println("Welcome, ADMIN");
        while (true){
            try {
                System.out.println("---------------");
                System.out.println("Press [1] to add new employees :");
                System.out.println("Press [2] to display all employees :");
                System.out.println("Press [3] to display all clients :");
                System.out.println("Press [4] to display all transactions :");
                System.out.println("Press [5] to logout :");

                int choice = input.nextInt();
                input.nextLine();
                switch (choice){
                    case 1:
                        addNewEmployee();
                        break;
                    case 2:
                        displayAllEmployees();
                        break;
                    case 3:
                        displayAllClients();
                        break;
                    case 4:
                        displayAllTransaction();
                        break;
                    case 5:
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