package pack1;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    Scanner input = new Scanner(System.in);

    public static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public Bank(){
        System.out.println("BANK MANAGEMENT SYSTEM");
        logIn();
    }


    public void logIn() {
        while (true) {
            System.out.println("---------------");
            System.out.println("Press [1] to Login");
            System.out.println("Press [2] to Exit");
            int num = input.nextInt();
            input.nextLine();
            if (num == 1){
                System.out.println("Enter Your username :");
                String username = input.nextLine();
                System.out.println("Enter your password :");
                String password = input.nextLine();

                boolean valid = false;

                if(username.equals("admin") && password.equals("admin")){
                    valid = true;
                }
                if (valid){
                    Admin.adminPage();
                } else {
                    int emp = 0;
                    for ( ; emp < Bank.employees.size(); emp++){
                        if (Bank.employees.get(emp).getUsername().equals(username) && Bank.employees.get(emp).getPassword().equals(password)){
                            valid = true;
                            break;
                        }
                    }
                    if (valid){
                        Bank.employees.get(emp).employeePage();
                    } else{
                        int cli = 0;
                        for ( ; cli < Bank.clients.size(); cli++){
                            if (Bank.clients.get(cli).getUsername().equals(username) && Bank.clients.get(cli).getPassword().equals(password)){
                                valid = true;
                                break;
                            }
                        }
                        if (valid){
                            Bank.clients.get(cli).clientPage();
                        } else {
                            System.out.println("Invalid username or password ");
                        }
                    }

                }
            } else if (num == 2) {
                break;
            } else {
                System.out.println("Invalid insertion");
                System.out.println("ReEnter your choice");
            }
        }
    }

}
