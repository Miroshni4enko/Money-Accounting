package model;

public class Account {
    public Integer accountId;
    public String name;
    public String address;
    public Integer phone;

    public Account(Integer accountId, String name, String address, Integer phone) {
        this.accountId = accountId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

}
