package com.example.autobill.db;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to keep track of multiple transactions.
 */
public class TransactionHistory {
    private final List<TransactionRecord> transactionList;

    public TransactionHistory() {
        transactionList = new ArrayList<>();
    }

    public void addTransaction(TransactionRecord transaction) {
        transactionList.add(transaction);
    }

    public List<TransactionRecord> getAllTransactions() {
        return new ArrayList<>(transactionList);
    }

    public int getTransactionCount() {
        return transactionList.size();
    }

    // Nested class representing a transaction record
    public static class TransactionRecord {
        private final String fromUser;
        private final String toUser;
        private final int amount;
        private final int status;

        public TransactionRecord(String fromUser, String toUser, int amount, int status) {
            this.fromUser = fromUser;
            this.toUser = toUser;
            this.amount = amount;
            this.status = status;
        }

        public String getFromUser() { return fromUser; }
        public String getToUser() { return toUser; }
        public int getAmount() { return amount; }
        public int getStatus() { return status; }
    }
}