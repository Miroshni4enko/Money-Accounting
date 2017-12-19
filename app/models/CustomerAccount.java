package models;

import io.ebean.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class CustomerAccount extends Model {
    @Id
    public Long accountNumber;
    @Constraints.Required
    public Integer balance;
    public String description;

    @ManyToOne()
    //@JsonIgnore
    public Customer customer;

    @OneToMany()
    @JsonIgnore
    public List<Transaction> transactions = new ArrayList<>();

    public static final Finder<Long, CustomerAccount> find = new Finder<>(CustomerAccount.class);

}
