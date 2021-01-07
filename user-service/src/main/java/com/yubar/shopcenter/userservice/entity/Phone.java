package com.yubar.shopcenter.userservice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Phone {

    private String id;
    private String number;
    private String verificationCode;
    private State state = State.REGISTERED;
    private Date createdDate = new Date();

    protected Phone() {
    }

    public Phone(String number, String verificationCode) {
        this.number = number;
        this.verificationCode = verificationCode;
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

    @NotEmpty
    public String getNumber() {
        return number;
    }

    private void setNumber(String number) {
        this.number = number;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    private void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @NotNull
    @Enumerated
    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedDate() {
        return createdDate;
    }

    private void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void expire() {
        if (state != State.REGISTERED)
            throw new IllegalStateException();
        setState(State.EXPIRED);
    }

    public void verify() {
        if (state != State.REGISTERED)
            throw new IllegalStateException();
        setState(State.VERIFIED);
    }

    public enum State {
        REGISTERED,
        EXPIRED,
        VERIFIED
    }
}
