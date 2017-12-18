package model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import io.ebean.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;
import play.db.ebean.Transactional;

@Entity
public class Transaction extends Model{
    @Id
    public Long transactionId;
    @Constraints.Required
    /*@Constraints.MaxLength(16)
    @Constraints.Min(1000000000000000)*/
    public Long externalAccountNumber;
    public Operation operation;
    @Constraints.Required
    public Integer amount;
    public String description;
    @Formats.DateTime(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date date;

    @ManyToOne()
    public CustomerAccount customerAccount;

    public static final Finder<Long, Transaction> find = new Finder<>(Transaction.class);



    @Transactional
    public static void credit(Transaction transaction){
        transaction.operation = Operation.Credit;
        if (transaction.customerAccount.balance < transaction.amount){
            throw new IllegalArgumentException();
        }else {
            transaction.customerAccount.balance = transaction.customerAccount.balance - transaction.amount;
            fillNeededDateAndSaveTransaction(transaction);
        }
    }
    @Transactional
    public static void debit(Transaction transaction){
        transaction.operation = Operation.Debit;
        System.out.println(transaction.customerAccount + ": "+ transaction.amount);
        transaction.customerAccount.balance = transaction.customerAccount.balance + transaction.amount;
        fillNeededDateAndSaveTransaction(transaction);
    }

    private static void fillNeededDateAndSaveTransaction(Transaction transaction){
        transaction.date = new Date();
        transaction.customerAccount.update();
        transaction.save();
    }
}
