package bank.account;

public class SavingsAccount extends Account {
    private final double interestRate; // Interest rate for the savings account

    // Constructor to initialize the savings account
    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance); // Call the base class constructor
        this.interestRate = interestRate; // Set interest rate
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {  // Ensure the deposit amount is valid
            balance += amount; // Update balance
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) { // Ensure withdrawal amount is valid
            balance -= amount; // Update balance
            return true; // Withdrawal successful
        }
        return false; // Insufficient balance or invalid amount
    }

    // Method to calculate interest for a given period (e.g., yearly)
    public double calculateInterest(int years) {
        return balance * interestRate * years; // Simple interest calculation
    }

    // Method to display account details
    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails(); // Call base class method
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
    }
}
