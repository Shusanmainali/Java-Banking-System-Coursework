package Part2;

import java.util.LinkedList; // Importing LinkedList class

import java.io.BufferedReader; // Importing BufferedReader class for reading files
import java.io.FileReader; // Importing FileReader class for reading files
import java.io.IOException; // Importing IOException class for handling IO exceptions

// Class representing a customer with first and last names
class Customer {

    // Private fields for first and last names
    private String firstName;
    private String lastName;

    // Getter method for first name
    public String getFirstName() {
        return this.firstName;
    }

    // Getter method for last name
    public String getLastName() {
        return this.lastName;
    }

    // Setter method for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Setter method for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

// Class representing an account, extending the Customer class
class Account extends Customer {

    // Private fields for balance and account number
    private int balance;
    private int accountNumber;

    // Constructor to initialize account with customer details, account number, and
    // balance
    Account(String FName, String LName, int accNum, int accBal) {

        // Set the first and last names using the setter methods from the Customer class
        setFirstName(FName);
        setLastName(LName);

        // Initialize account number and balance
        this.accountNumber = accNum;
        this.balance = accBal;
    }

    // Getter method for balance
    public int getBalance() {
        return this.balance;
    }

    // Getter method for account number
    public int getAccountNum() {
        return this.accountNumber;
    }

    // Method to deposit an amount into the account
    public void deposit(int amount) {
        this.balance += amount;
    }

    // Method to withdraw an amount from the account
    public void withdraw(int amount) {
        this.balance -= amount;
    }
}

// Class representing a transaction
class Transaction {
    // Method to transfer an amount from one account to another
    public void transfer(Account acc1, Account acc2, int amount) {
        // Withdraw the amount from the first account
        acc1.withdraw(amount);
        // Deposit the amount into the second account
        acc2.deposit(amount);
    }
}

// Class to read account information from a file
class ReadAccounts {
    // LinkedLists to store first names, last names, account numbers, and balances
    private LinkedList<String> firstNames = new LinkedList<>();
    private LinkedList<String> lastNames = new LinkedList<>();
    private LinkedList<Integer> accounts = new LinkedList<>();
    private LinkedList<Integer> balances = new LinkedList<>();

    // Constructor to read account information from a CSV file
    ReadAccounts(String URL) {

        try (BufferedReader reader = new BufferedReader(new FileReader(URL))) {
            String line;
            while ((line = reader.readLine()) != null) {

                // Splitting using commas because that's the CSV format
                String[] values = line.split(",");

                // Adding the values to the corresponding LinkedLists
                this.firstNames.add(values[0]);
                this.lastNames.add(values[1]);
                this.accounts.add(Integer.parseInt(values[2]));
                this.balances.add(Integer.parseInt(values[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter methods to return the LinkedLists
    public LinkedList<String> getFirstNames() {
        return this.firstNames;
    }

    public LinkedList<String> getLastNames() {
        return this.lastNames;
    }

    public LinkedList<Integer> getAccounts() {
        return this.accounts;
    }

    public LinkedList<Integer> getBalances() {
        return this.balances;
    }
}

public class Main {

    public static void main(String[] args) {

        String filePath = "Accounts.csv"; // Path to the CSV file
        ReadAccounts readAccs = new ReadAccounts(filePath); // Creating an instance of ReadAccounts

        // Individual LinkedLists to store all individual data
        LinkedList<String> firstNames = readAccs.getFirstNames();
        LinkedList<String> lastNames = readAccs.getLastNames();
        LinkedList<Integer> accountList = readAccs.getAccounts();
        LinkedList<Integer> balanceList = readAccs.getBalances();

        LinkedList<Account> accounts = new LinkedList<>(); // LinkedList to store all of our accounts

        // Loop to create accounts and add them to the accounts LinkedList
        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
        }

        Account account1 = accounts.get(0); // Getting the first account
        Account account2 = accounts.get(1); // Getting the second account

        System.out.println(account1.getBalance()); // Printing the balance of the first account
        System.out.println(account2.getBalance()); // Printing the balance of the second account

        account1.deposit(250); // Depositing 250 into the first account
        System.out.println(account1.getBalance()); // Printing the new balance of the first account

        account2.withdraw(500); // Withdrawing 500 from the second account
        System.out.println(account2.getBalance()); // Printing the new balance of the second account

        Transaction t = new Transaction(); // Creating an instance of Transaction
        t.transfer(account1, account2, 250); // Transferring 250 from the first account to the second account

        System.out.println("New Balance: ");
        System.out.println(account1.getBalance()); // Printing the new balance of the first account
        System.out.println(account2.getBalance()); // Printing the new balance of the second account
    }
}
