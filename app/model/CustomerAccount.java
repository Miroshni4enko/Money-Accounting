package model;

import io.ebean.*;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.ArrayList;
import java.util.List;


@Entity
public class CustomerAccount extends Model {
    @Id
    /*@Constraints.MaxLength(16)
    @Constraints.Min(1000000000000000)*/
    public Long accountNumber;
    @Constraints.Required
    public Integer balance;
    public String description;


    @ManyToOne()
    public Customer customer;

    @OneToMany()
    public List<Transaction> transactions = new ArrayList<>();

    public static final Finder<Long, CustomerAccount> find = new Finder<>(CustomerAccount.class);

}
