package pack1;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;

public class Bank {
    Scanner input = new Scanner(System.in);

    public static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    // files path
    String EmployeePath = "D:\\projects\\intelij\\BankManagementSystem\\src\\files\\Employee.csv";
    String ClientPath = "D:\\projects\\intelij\\BankManagementSystem\\src\\files\\Client.csv";
    String TransactionPath = "D:\\projects\\intelij\\BankManagementSystem\\src\\files\\Transaction.csv";
    String AccountPath = "D:\\projects\\intelij\\BankManagementSystem\\src\\files\\Account.csv";



    public Bank() {
        System.out.println("BANK MANAGEMENT SYSTEM");



        logIn();

        writeToFile(EmployeePath,"employee");
        writeToFile(ClientPath,"client");
        writeToFile(TransactionPath,"transaction");
        writeToFile(AccountPath,"account");
    }

    public void logIn() {
        while (true) {
            try {
                System.out.println("---------------");
                System.out.println("Press [1] to Login");
                System.out.println("Press [2] to Exit");

                int num = input.nextInt();
                input.nextLine(); // Consume the newline character

                if (num == 1) {
                    System.out.println("Enter Your username:");
                    String username = input.nextLine();
                    System.out.println("Enter your password:");
                    String password = input.nextLine();

                    boolean valid = false;

                    if (username.equals("admin") && password.equals("admin")) {
                        valid = true;
                    }

                    if (valid) {
                        Admin.adminPage();
                    } else {
                        int emp = 0;
                        for (; emp < Bank.employees.size(); emp++) {
                            if (Bank.employees.get(emp).getUsername().equals(username) && Bank.employees.get(emp).getPassword().equals(password)) {
                                valid = true;
                                break;
                            }
                        }

                        if (valid) {
                            Bank.employees.get(emp).employeePage();
                        } else {
                            int cli = 0;
                            for (; cli < Bank.clients.size(); cli++) {
                                if (Bank.clients.get(cli).getUsername().equals(username) && Bank.clients.get(cli).getPassword().equals(password)) {
                                    valid = true;
                                    break;
                                }
                            }

                            if (valid) {
                                Bank.clients.get(cli).clientPage();
                            } else {
                                System.out.println("Invalid username or password");
                            }
                        }
                    }
                } else if (num == 2) {  // for exit choice
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                input.nextLine(); // Clear input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }


    private void writeToFile(String fileName, String type){
        try (FileWriter writer = new FileWriter(fileName)) {
            if (type.equals("employee")){
                for (Employee emp : Bank.employees){
                    writer.append(String.valueOf(emp.getID())).append(",")
                            .append(emp.getFirstName()).append(",")
                            .append(emp.getLastName()).append(",")
                            .append(emp.getUsername()).append(",")
                            .append(emp.getPassword()).append(",")
                            .append(emp.getAddress()).append(",")
                            .append(emp.getTelephoneNumber()).append(",")
                            .append(emp.getGraduatedCollege()).append(",")
                            .append(emp.getTotalGrade()).append(",")
                            .append(String.valueOf(emp.getYearOfGraduation())).append(",\n");
                }
            } else if (type.equals("client")) {
                for (Client cli : Bank.clients){
                    writer.append(String.valueOf(cli.getID())).append(",")
                            .append(cli.getFirstName()).append(",")
                            .append(cli.getLastName()).append(",")
                            .append(cli.getUsername()).append(",")
                            .append(cli.getPassword()).append(",")
                            .append(cli.getAddress()).append(",")
                            .append(cli.getTelephoneNumber()).append(",")
                            .append(cli.getCreatedBy()).append(",\n");
                }
            } else if (type.equals("account")) {
                for (Account acc : Bank.accounts){
                    writer.append(acc.getClientName()).append(",")
                            .append(String.valueOf(acc.getAccountNumber())).append(",")
                            .append(acc.getAccountStatus()).append(",")
                            .append(acc.getAccountType()).append(",")
                            .append(String.valueOf(acc.getBalance())).append(",")
                            .append(String.valueOf(acc.getHasCreditCard())).append(",\n");
                }
            } else if (type.equals("transaction")){
                for (Transaction trans : Bank.transactions){
                    if (trans instanceof TransferTransaction){
                        writer.append(String.valueOf(trans.getId())).append(",")
                                .append(trans.getClientName()).append(",")
                                .append(trans.getTransactionDate()).append(",")
                                .append(String.valueOf(trans.getAmount())).append(",")
                                .append("transferTransaction").append(",")
                                .append(String.valueOf(((TransferTransaction)trans).getSenderAccount())).append(",")
                                .append(String.valueOf(((TransferTransaction)trans).getRecipientAccount())).append(",\n");
                    } else if (trans instanceof WithDrawTransaction) {
                        writer.append(String.valueOf(trans.getId())).append(",")
                                .append(trans.getClientName()).append(",")
                                .append(trans.getTransactionDate()).append(",")
                                .append(String.valueOf(trans.getAmount())).append(",")
                                .append("withdrawTransaction").append(",")
                                .append(String.valueOf(((WithDrawTransaction)trans).getAccountNumber())).append(",\n");
                    } else if (trans instanceof DepositTransaction) {
                        writer.append(String.valueOf(trans.getId())).append(",")
                                .append(trans.getClientName()).append(",")
                                .append(trans.getTransactionDate()).append(",")
                                .append(String.valueOf(trans.getAmount())).append(",")
                                .append("depositTransaction").append(",")
                                .append(String.valueOf(((DepositTransaction)trans).getAccountNumber())).append(",\n");
                    } else {
                        writer.append(String.valueOf(trans.getId())).append(",")
                                .append(trans.getClientName()).append(",")
                                .append(trans.getTransactionDate()).append(",")
                                .append(String.valueOf(trans.getAmount())).append(",")
                                .append("creditCardTransaction").append(",")
                                .append(String.valueOf(((CreditCardTransaction)trans).getAccountNumber())).append(",\n");
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



























