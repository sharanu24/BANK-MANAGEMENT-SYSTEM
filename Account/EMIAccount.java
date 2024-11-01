package bank.account;

public class EMIAccount extends Account {
    private double loanAmount;
    private final double interestRate;
    private final int tenureInMonths;
    private final double emiAmount;

    public EMIAccount(String accountNumber, double loanAmount, double interestRate, int tenureInMonths) {
        super(accountNumber, loanAmount); // Initial balance equals loan amount
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenureInMonths = tenureInMonths;
        this.emiAmount = calculateEMI();
    }

    private double calculateEMI() {
        double monthlyRate = (interestRate / 100) / 12;
        return (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenureInMonths)) /
                (Math.pow(1 + monthlyRate, tenureInMonths) - 1);
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    @Override
    public void deposit(double amount) {

    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Loan Amount: $" + loanAmount);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Tenure: " + tenureInMonths + " months");
        System.out.println("Monthly EMI: $" + emiAmount);
    }

    public boolean payEMI() {
        if (balance >= emiAmount) {
            balance -= emiAmount;
            loanAmount -= emiAmount;
            System.out.println("EMI paid successfully. Remaining loan amount: $" + loanAmount);
            return true;
        } else {
            System.out.println("Insufficient balance for EMI payment.");
            return false;
        }
    }

    public boolean isLoanCleared() {
        return loanAmount <= 0;
    }
}
