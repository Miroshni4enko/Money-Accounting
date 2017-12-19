package models;

import io.ebean.Finder;
import io.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends Model {
    @Id
    public Long customerId;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String address;
    @Constraints.Required
    public Integer phone;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    public List<CustomerAccount> accounts = new ArrayList<>();

    public static final Finder<Long, Customer> find = new Finder<>(Customer.class);
}
