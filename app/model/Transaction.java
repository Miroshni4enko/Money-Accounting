package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Transaction {
    public Integer Id;
    public String from;
    public String to;
    public Operation operation;
    public Integer amount;
    public Date date;

    public Transaction(Integer id, String from, String to, Operation operation, Integer amount, Date date) {
        Id = id;
        this.from = from;
        this.to = to;
        this.operation = operation;
        this.amount = amount;
        this.date = date;
    }

    private static Set<Transaction> transactions;

    static {
        transactions = new HashSet<>();
        transactions.add(new Transaction(1, "Slava", "Vlad", Operation.Credit, 100, new Date()));
        transactions.add(new Transaction(2, "Slava1", "Vlad1", Operation.Debit, 200, new Date()));
        transactions.add(new Transaction(3, "Slava2", "Vlad2", Operation.Credit, 300, new Date()));
    }

    public static Set<Transaction> getTransactionHistory() {
        return transactions;
    }
    public static Transaction findById(){

    }
}
