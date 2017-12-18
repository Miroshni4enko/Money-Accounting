package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.avaje.ebean.Model;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

public class Transaction extends Model{
    @Id
    public Long transactionId;
    public Integer externalAccount;
    @Constraints.Required
    public Operation operation;
    @Constraints.Required
    public Integer amount;
    @Formats.DateTime(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date date;

    public static final Finder<Long, Transaction> find = new Finder<>(Transaction.class);

    /*private static Set<Transaction> transactions;

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
*/
}
