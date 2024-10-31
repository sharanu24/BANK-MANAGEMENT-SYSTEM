package bank.service;

import bank.account.*;
import bank.utils.Logger;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, EMIAccount> emiAccounts = new HashMap<>();

    public boolean createEMIAccount(String accountNumber, double loanAmount, double interestRate, int tenureInMonths) {
        if (!emiAccounts.containsKey(accountNumber)) {
            EMIAccount emiAccount = new EMIAccount(accountNumber, loanAmount, interestRate, tenureInMonths);
            emiAccounts.put(accountNumber, emiAccount);
            return true;
        }
        System.out.println("Account already exists.");
        return false;
    }

    public boolean payEMI(String accountNumber) {
        EMIAccount emiAccount = emiAccounts.get(accountNumber);
        if (emiAccount != null) {
            return emiAccount.payEMI();
        }
        System.out.println("Account not found.");
        return false;
    }

    public void displayEMIAccountDetails(String accountNumber) {
        EMIAccount emiAccount = emiAccounts.get(accountNumber);
        if (emiAccount != null) {
            emiAccount.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    public boolean createSavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        if (accounts.containsKey(accountNumber)) {
            Logger.log("Account creation failed: Duplicate account number.");
            return false;
        }
        accounts.put(accountNumber, new SavingsAccount(accountNumber, initialBalance, interestRate));
        return true;
    }

    public boolean createCurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        if (accounts.containsKey(accountNumber)) {
            Logger.log("Account creation failed: Duplicate account number.");
            return false;
        }
        accounts.put(accountNumber, new CurrentAccount(accountNumber, initialBalance, overdraftLimit));
        return true;
    }

    public boolean createFixedDepositAccount(String accountNumber, double initialBalance, double interestRate, int termInMonths, double penaltyRate) {
        if (accounts.containsKey(accountNumber)) {
            Logger.log("Account creation failed: Duplicate account number.");
            return false;
        }
        accounts.put(accountNumber, new FixedDepositAccount(accountNumber, initialBalance, interestRate, termInMonths, penaltyRate));
        return true;
    }

    public boolean createRecurringDepositAccount(String accountNumber, double monthlyDeposit, double interestRate, int totalMonths) {
        if (accounts.containsKey(accountNumber)) {
            Logger.log("Account creation failed: Duplicate account number.");
            return false;
        }
        accounts.put(accountNumber, new RecurringDepositAccount(accountNumber, monthlyDeposit, interestRate, totalMonths));
        return true;
    }



    public boolean deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        Logger.log("Deposit failed: Account not found.");
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        return account != null && account.withdraw(amount);
    }

    public Double checkBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : null;
    }
}
