package bank.account;

public class RecurringDepositAccount extends Account {
    private final double monthlyDeposit;
    private final double interestRate;
    private final int totalMonths;
    private int monthsPaid;

    public RecurringDepositAccount(String accountNumber, double monthlyDeposit, double interestRate, int totalMonths) {
        super(accountNumber, 0);  // Balance starts at 0, accumulates over time
        this.monthlyDeposit = monthlyDeposit;
        this.interestRate = interestRate;
        this.totalMonths = totalMonths;
        this.monthsPaid = 0;
    }

    @Override
    public void deposit(double amount) {
        if (monthsPaid < totalMonths && amount == monthlyDeposit) {
            balance += amount;
            monthsPaid++;
        } else {
            System.out.println("Invalid deposit. Monthly deposit should match the agreed amount.");
        }
    }

    @Override
    public boolean withdraw(double amount) {
        if (monthsPaid == totalMonths) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
        } else {
            System.out.println("Withdrawals are not allowed until the account is fully matured.");
        }
        return false;
    }

    public void addInterest() {
        if (monthsPaid == totalMonths) {
            balance += balance * (interestRate / 100);
        }
    }
}
