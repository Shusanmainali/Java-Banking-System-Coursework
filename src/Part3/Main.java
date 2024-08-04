package Part3;

import java.util.LinkedList; // Importing LinkedList class
import java.io.BufferedReader; // Importing BufferedReader class for reading files
import java.io.FileReader; // Importing FileReader class for reading files
import java.io.IOException; // Importing IOException class for handling IO exceptions

import javax.swing.*; // Importing Swing components for GUI
import java.awt.event.*; // Importing event handling classes

// Class representing a customer with first and last names
class Customer {
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
    private int balance;
    private int accountNumber;

    // Constructor to initialize account with customer details, account number, and balance
    Account(String FName, String LName, int accNum, int accBal) {
        setFirstName(FName);
        setLastName(LName);
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
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}

// Class to read account information from a file
class ReadAccounts {
    private LinkedList<String> firstNames = new LinkedList<>();
    private LinkedList<String> lastNames = new LinkedList<>();
    private LinkedList<Integer> accounts = new LinkedList<>();
    private LinkedList<Integer> balances = new LinkedList<>();

    // Constructor to read account information from a CSV file
    ReadAccounts(String URL) {
        try (BufferedReader reader = new BufferedReader(new FileReader(URL))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Splitting using commas because that's the CSV format
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

// Class representing the GUI for the banking system
class GUI extends JFrame {
    private Transaction transferObject = new Transaction();
    private StringBuilder sbAllData = new StringBuilder();
    private LinkedList<Account> globalAccounts;

    // GUI components
    private JLabel showAllData, depositHeading, withdrawHeading, transferHeading;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField depositInput, withdrawInput, transferAmount, accDeposit, accWithdraw, acc1Transfer, acc2Transfer;

    // Constructor to initialize the GUI
    GUI(LinkedList<Account> accounts) {
        super("Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        this.globalAccounts = accounts;

        // Initialize Components
        initializeComponents();

        // Set Component Bounds
        setComponentBounds();

        // Add Components to Frame
        addComponentsToFrame();

        // Add Event Listeners
        addEventListeners();
    }

    // Method to initialize GUI components
    private void initializeComponents() {
        // Initialize Buttons
        showAllButton = new JButton("Show all");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");

        // Initialize TextFields
        depositInput = new JTextField();
        depositInput.setToolTipText("Deposit Amount");
        accDeposit = new JTextField();
        accDeposit.setToolTipText("Deposit Account No.");
        withdrawInput = new JTextField();
        withdrawInput.setToolTipText("Withdraw Amount");
        accWithdraw = new JTextField();
        accWithdraw.setToolTipText("Withdraw Account No.");
        transferAmount = new JTextField();
        transferAmount.setToolTipText("Transfer Amount");
        acc1Transfer = new JTextField();
        acc1Transfer.setToolTipText("From Account No.");
        acc2Transfer = new JTextField();
        acc2Transfer.setToolTipText("To Account No.");

        // Initialize Labels
        showAllData = new JLabel("Show all data.");
        depositHeading = new JLabel("Deposit Section:");
        withdrawHeading = new JLabel("Withdraw Section:");
        transferHeading = new JLabel("Transfer Section:");
    }

    // Method to set bounds for GUI components
    private void setComponentBounds() {
        // Set Bounds for Labels
        depositHeading.setBounds(20, 10, 200, 30);
        accDeposit.setBounds(20, 40, 200, 30);
        depositInput.setBounds(20, 80, 200, 30);
        withdrawHeading.setBounds(20, 120, 200, 30);
        accWithdraw.setBounds(20, 150, 200, 30);
        withdrawInput.setBounds(20, 190, 200, 30);
        transferHeading.setBounds(20, 230, 200, 30);
        acc1Transfer.setBounds(20, 260, 200, 30);
        acc2Transfer.setBounds(20, 300, 200, 30);
        transferAmount.setBounds(20, 340, 200, 30);
        showAllButton.setBounds(20, 380, 120, 30);
        depositButton.setBounds(230, 40, 120, 30);
        withdrawButton.setBounds(230, 150, 120, 30);
        transferButton.setBounds(230, 260, 120, 30);
        showAllData.setBounds(20, 420, 550, 125);
    }

    // Method to add components to the frame
    private void addComponentsToFrame() {
        // Add Labels to Frame
        add(depositHeading);
        add(withdrawHeading);
        add(transferHeading);
        add(showAllData);

        // Add TextFields to Frame
        add(depositInput);
        add(accDeposit);
        add(withdrawInput);
        add(accWithdraw);
        add(transferAmount);
        add(acc1Transfer);
        add(acc2Transfer);

        // Add Buttons to Frame
        add(showAllButton);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
    }

    // Method to add event listeners to the buttons
    private void addEventListeners() {
        HandlerClass handler = new HandlerClass();
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
        showAllButton.addActionListener(handler);
    }

    // Method to update the UI with the latest account data
    private void updateUI() {
        sbAllData.setLength(0);
        for (Account account : globalAccounts) {
            sbAllData.append(account.getFirstName() + " " + account.getLastName() + "    ");
            sbAllData.append(account.getAccountNum() + "    ");
            sbAllData.append(account.getBalance() + "    ");
        }
        showAllData.setText(sbAllData.toString());
    }

    // Inner class to handle button actions
    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == showAllButton) {
                    updateUI();
                } else if (e.getSource() == depositButton) {
                    handleDeposit();
                } else if (e.getSource() == withdrawButton) {
                    handleWithdraw();
                } else if (e.getSource() == transferButton) {
                    handleTransfer();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter numeric values.");
            }
        }

        // Method to handle deposit action
        private void handleDeposit() {
            int accountNumber = Integer.parseInt(accDeposit.getText());
            int amount = Integer.parseInt(depositInput.getText());
            for (Account account : globalAccounts) {
                if (account.getAccountNum() == accountNumber) {
                    account.deposit(amount);
                    updateUI();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Account not found!");
        }

        // Method to handle withdraw action
        private void handleWithdraw() {
            int accountNumber = Integer.parseInt(accWithdraw.getText());
            int amount = Integer.parseInt(withdrawInput.getText());
            for (Account account : globalAccounts) {
                if (account.getAccountNum() == accountNumber) {
                    if (account.getBalance() >= amount) {
                        account.withdraw(amount);
                        updateUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Account not found!");
        }

        // Method to handle transfer action
        private void handleTransfer() {
            int accountNumber1 = Integer.parseInt(acc1Transfer.getText());
            int accountNumber2 = Integer.parseInt(acc2Transfer.getText());
            int amount = Integer.parseInt(transferAmount.getText());

            Account account1 = null, account2 = null;

            for (Account account : globalAccounts) {
                if (account.getAccountNum() == accountNumber1) {
                    account1 = account;
                } else if (account.getAccountNum() == accountNumber2) {
                    account2 = account;
                }
                if (account1 != null && account2 != null) {
                    break;
                }
            }

            if (account1 != null && account2 != null) {
                if (account1.getBalance() >= amount) {
                    transferObject.transfer(account1, account2, amount);
                    updateUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient funds for transfer!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "One or both accounts not found!");
            }
        }
    }
}

// Main class to run the application
public class Main {
    public static void main(String[] args) {
        String filePath = "Accounts.csv"; // Specify the path to your CSV file
        ReadAccounts readAccs = new ReadAccounts(filePath);

        LinkedList<String> firstNames = readAccs.getFirstNames();
        LinkedList<String> lastNames = readAccs.getLastNames();
        LinkedList<Integer> accountList = readAccs.getAccounts();
        LinkedList<Integer> balanceList = readAccs.getBalances();

        LinkedList<Account> accounts = new LinkedList<>();

        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountList.get(i), balanceList.get(i)));
        }

        GUI gui = new GUI(accounts);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1400, 1000);
        gui.setVisible(true);
    }
}
