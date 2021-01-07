package com.yubar.shopcenter.userservice.model;

import com.yubar.shopcenter.userservice.entity.Phone;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class PhoneModel {

    private String id;
    private String number;
    private String verificationCode;
    private Phone.State state = Phone.State.REGISTERED;
    private Date createdDate = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Phone.State getState() {
        return state;
    }

    public void setState(Phone.State state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static PhoneModel modelOf(Phone phone) {
        PhoneModel model = new PhoneModel();
        BeanUtils.copyProperties(phone, model);
        return model;
    }
}
