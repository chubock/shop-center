package com.yubar.shopcenter.customerservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
public class Address implements Serializable {

    private String id;
    private Location location;
    private String text;
    private String phoneNumber;
    private Customer customer;

    Address() {
    }

    public Address(Customer customer, Location location, String text) {
        this.customer = customer;
        this.location = location;
        this.text = text;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Embedded
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @NotEmpty
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Customer getCustomer() {
        return customer;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
