package bank.account;

public abstract class Account {
    protected String accountNumber; // Unique identifier for the account
    protected double balance;        // Current balance in the account

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber; // Set account number
        this.balance = initialBalance;       // Set initial balance
    }

    public abstract void deposit(double amount); // Abstract method for deposit

    public abstract boolean withdraw(double amount); // Abstract method for withdrawal

    public String getAccountNumber() {
        return accountNumber; // Getter for account number
    }

    public double getBalance() {
        return balance; // Getter for balance
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
    }
}
