package model;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.Constraint;

public class Account extends Model {
    @Id
    public Long accountId;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String address;
    @Constraints.Required
    public Integer phone;

    public static final Finder<Long, Account> find = new Finder<>(Account.class);

}
