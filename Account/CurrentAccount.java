package bank.account;

public class CurrentAccount extends Account {
    private final double overdraftLimit; // Overdraft limit for the current account

    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance); // Call the base class constructor
        this.overdraftLimit = overdraftLimit; // Set overdraft limit
    }

    @Override
    public void deposit(double amount) {
        balance += amount; // Update balance
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= (balance + overdraftLimit)) { // Allow overdraft
            balance -= amount; // Update balance
            return true; // Withdrawal successful
        }
        return false; // Insufficient balance and overdraft limit exceeded
    }

    // Method to display account details
    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails(); // Call base class method
        System.out.println("Overdraft Limit: $" + overdraftLimit);
    }
}
