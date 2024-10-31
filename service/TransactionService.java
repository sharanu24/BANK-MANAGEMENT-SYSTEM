package bank.service;

import bank.transactions.Transaction;
import bank.transaction.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<Transaction> transactionHistory = new ArrayList<>();

    public void recordTransaction(String transactionId, double amount, TransactionType type) {
        Transaction transaction = new Transaction(transactionId, amount, type);
        transactionHistory.add(transaction);
    }

    public void displayTransactionHistory() {
        for (Transaction transaction : transactionHistory) {
            transaction.displayTransaction();
            System.out.println("---------------------------");
        }
    }
}
