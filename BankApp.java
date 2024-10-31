package bank;

import bank.service.AccountService;
import bank.service.TransactionService;
import bank.transaction.TransactionType;
import bank.utils.InputValidator;
import bank.utils.Logger;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bank Management System");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Create Savings Account\n2. Create Current Account\n3. Create Fixed Deposit Account\n4. Create Recurring Deposit Account\n5. Deposit\n6. Withdraw\n7. Check Balance\n8. View Transaction History\n9. Exit");
            System.out.println("9. Create EMI Account");
            System.out.println("10. Pay EMI");
            System.out.println("11. View EMI Account Details");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Savings Account
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    double interestRate = scanner.nextDouble();
                    if (accountService.createSavingsAccount(accountNumber, initialBalance, interestRate)) {
                        Logger.log("Savings account created successfully!");
                    }
                    break;

                case 2:
                    // Current Account
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter Initial Balance: ");
                    initialBalance = scanner.nextDouble();
                    System.out.print("Enter Overdraft Limit: ");
                    double overdraftLimit = scanner.nextDouble();
                    if (accountService.createCurrentAccount(accountNumber, initialBalance, overdraftLimit)) {
                        Logger.log("Current account created successfully!");
                    }
                    break;

                case 3:
                    // Fixed Deposit Account
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter Initial Balance: ");
                    initialBalance = scanner.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    interestRate = scanner.nextDouble();
                    System.out.print("Enter Term (months): ");
                    int termInMonths = scanner.nextInt();
                    System.out.print("Enter Penalty Rate for Early Withdrawal: ");
                    double penaltyRate = scanner.nextDouble();
                    if(initialBalance<50000) {
                        Logger.log("Unable to  create FixedDepositAccount. Minimum deposit amount should be 50000");
                    }
                   else
                    {
                        accountService.createFixedDepositAccount(accountNumber, initialBalance, interestRate, termInMonths, penaltyRate);
                        Logger.log("Fixed Deposit account created successfully!");
                    }
                    break;

                case 4:
                    // Recurring Deposit Account
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter Monthly Deposit Amount: ");
                    double monthlyDeposit = scanner.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    interestRate = scanner.nextDouble();
                    System.out.print("Enter Total Term (months): ");
                    int totalMonths = scanner.nextInt();

                    if (accountService.createRecurringDepositAccount(accountNumber, monthlyDeposit, interestRate, totalMonths)) {
                        Logger.log("Recurring Deposit account created successfully!");
                    }
                    break;

                case 5:
                    // Deposit
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (InputValidator.isValidAmount(depositAmount) && accountService.deposit(accountNumber, depositAmount)) {
                        transactionService.recordTransaction("TXN" + System.currentTimeMillis(), depositAmount, TransactionType.DEPOSIT);
                        Logger.log("Deposit successful!");
                    } else {
                        Logger.log("Deposit failed. Invalid account number or amount.");
                    }
                    break;

                case 6:
                    // Withdraw
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (InputValidator.isValidAmount(withdrawAmount) && accountService.withdraw(accountNumber, withdrawAmount)) {
                        transactionService.recordTransaction("TXN" + System.currentTimeMillis(), withdrawAmount, TransactionType.WITHDRAWAL);
                        Logger.log("Withdrawal successful!");
                    } else {
                        Logger.log("Withdrawal failed. Invalid account number or insufficient balance.");
                    }
                    break;

                case 7:
                    // Check Balance
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    Double balance = accountService.checkBalance(accountNumber);
                    if (balance != null) {
                        System.out.println("Account Balance: " + balance);
                    } else {
                        System.out.println("Invalid account number.");
                    }
                    break;

                case 8:
                    // Transaction History
                    System.out.println("Transaction History:");
                    transactionService.displayTransactionHistory();
                    break;

                case 9:
                    // Create EMI Account
                    System.out.print("Enter Account Number: ");
                    String emiAccountNumber = scanner.next();
                    System.out.print("Enter Loan Amount: ");
                    double loanAmount = scanner.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    double loanInterestRate = scanner.nextDouble();
                    System.out.print("Enter Tenure in Months: ");
                    int tenureInMonths = scanner.nextInt();

                    if (accountService.createEMIAccount(emiAccountNumber, loanAmount, loanInterestRate, tenureInMonths)) {
                        Logger.log("EMI Account created successfully!");
                    }
                    break;

                case 10:
                    // Pay EMI
                    System.out.print("Enter Account Number: ");
                    emiAccountNumber = scanner.next();
                    if (accountService.payEMI(emiAccountNumber)) {
                        Logger.log("EMI payment successful!");
                    } else {
                        Logger.log("EMI payment failed.");
                    }
                    break;

                case 11:
                    // Display EMI Account Details
                    System.out.print("Enter Account Number: ");
                    emiAccountNumber = scanner.next();
                    accountService.displayEMIAccountDetails(emiAccountNumber);
                    break;


                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        scanner.close();
    }
}


/*
STATE BANK OF INDIA (SBI)
Savings Account Interest Rate:  2.70% per annum
Fixed Deposit (FD) Interest Rates:3.00% to 6.25% per annum
Recurring Deposit (RD) Interest Rates:  3.00% to 6.25% per annum
Loan Interest Rates: 9%
*/