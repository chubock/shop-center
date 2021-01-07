package com.yubar.shopcenter.customerservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Customer implements Serializable {

    private String id;
    private String mobile;
    private String firstName;
    private String lastName;
    private List<Address> addresses = new ArrayList<>();

    Customer() {
    }

    public Customer(String id, String mobile, String firstName, String lastName) {
        this.id = id;
        this.mobile = mobile;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @Column(unique = true)
    public String getMobile() {
        return mobile;
    }

    private void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public List<Address> getAddresses() {
        return addresses;
    }

    private void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
