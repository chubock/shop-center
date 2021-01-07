package com.yubar.shopcenter.customerservice.model;

import com.yubar.shopcenter.customerservice.entity.Address;
import com.yubar.shopcenter.customerservice.entity.Location;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressModel {

    private String id;
    @NotNull
    private Location location;
    @NotEmpty
    private String text;
    @NotEmpty
    private String phoneNumber;
    private String customerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public static AddressModel modelOf(Address address) {
        AddressModel model = new AddressModel();
        BeanUtils.copyProperties(address, model);
        if (address.getCustomer() != null)
            model.setCustomerId(address.getCustomer().getId());
        return model;
    }
}
