package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Transaction {
    private Integer transactionId;
    private Integer externalAccount;
    private Operation operation;
    private Integer amount;
    private Date date;

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getExternalAccount() {
        return externalAccount;
    }

    public Operation getOperation() {
        return operation;
    }

    public Integer getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setExternalAccount(Integer externalAccount) {
        this.externalAccount = externalAccount;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void setTransactions(Set<Transaction> transactions) {
        Transaction.transactions = transactions;
    }

    public Transaction() {
    }

    public Transaction(int transactionId, int externalAccount, Operation operation, int amount, Date date) {
        this.transactionId = transactionId;
        this.externalAccount = externalAccount;
        this.operation = operation;
        this.amount = amount;
        this.date = date;
    }

    private static Set<Transaction> transactions;

    static {
        transactions = new HashSet<>();
        transactions.add(new Transaction(1, 2, Operation.Credit, 100, new Date()));
        transactions.add(new Transaction(2, 2, Operation.Debit, 200, new Date()));
        transactions.add(new Transaction(3, 2, Operation.Credit, 300, new Date()));
    }

    public static Set<Transaction> getTransactionHistory() {
        return transactions;
    }

    public static Transaction findById(int transactionId){
        for(Transaction t : transactions){
            if (t.transactionId == transactionId){
                return t;
            }
        }
        return null;
    }

    public static void credit( Transaction transaction){
        transactions.add(transaction);
    }

    public static void debit(Transaction transaction){
        transaction.date = new Date();
        transaction.transactionId = 4;
        transaction.operation = Operation.Debit;
        transactions.add(transaction);
    }

}
