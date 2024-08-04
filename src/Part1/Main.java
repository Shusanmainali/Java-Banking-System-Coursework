package Part1;

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

    // Constructor to initialize account with customer details, account number, and balance
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


public class Main {

    public static void main(String[] args) {
        Account account1 = new Account("Jeffrey", "Ting", 1, 2000);
        Account account2 = new Account("Hiran", "Patel", 2, 1000);

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

        account1.deposit(250);
        System.out.println(account1.getBalance());

        account2.withdraw(500);
        System.out.println(account2.getBalance());

        Transaction t = new Transaction();
        t.transfer(account1, account2, 250);

        System.out.println("New Balance: ");
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
    }

}