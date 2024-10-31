package bank.account;

public class FixedDepositAccount extends Account {
    private final double interestRate;
    private int termInMonths;
    private final double penaltyRate;
    private boolean matured = false;

    public FixedDepositAccount(String accountNumber, double initialBalance, double interestRate, int termInMonths, double penaltyRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.penaltyRate = penaltyRate;
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Deposits are not allowed after the initial deposit in a Fixed Deposit Account.");
    }

    @Override
    public boolean withdraw(double amount) {
        if (matured || termInMonths <= 0) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
        } else {
            double penaltyAmount = amount * (penaltyRate / 100);
            double totalAmount = amount + penaltyAmount;
            if (balance >= totalAmount) {
                balance -= totalAmount;
                System.out.println("Early withdrawal with penalty: " + penaltyAmount);
                return true;
            }
        }
        System.out.println("Withdrawal failed. Either insufficient balance or account not matured.");
        return false;
    }

    public void addInterest() {
        if (termInMonths > 0) {
            balance += balance * (interestRate / 100);
            termInMonths--;
        } else {
            matured = true;
        }
    }
}
