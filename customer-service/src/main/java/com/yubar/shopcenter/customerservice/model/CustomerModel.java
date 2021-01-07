package com.yubar.shopcenter.customerservice.model;

import com.yubar.shopcenter.customerservice.entity.Customer;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    private String id;
    @NotEmpty
    private String mobile;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private List<AddressModel> addressModels = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AddressModel> getAddressModels() {
        return addressModels;
    }

    public void setAddressModels(List<AddressModel> addressModels) {
        this.addressModels = addressModels;
    }

    public static CustomerModel modelOf(Customer customer) {
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(customer, model);
        return model;
    }
}
