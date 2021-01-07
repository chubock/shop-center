package com.yubar.shopcenter.userservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private Authority authority;

    User() {
    }

    public User(String username, Authority authority) {
        this.username = username;
        this.authority = authority;
    }

    @Id
    @GeneratedValue
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Enumerated
    public Authority getAuthority() {
        return authority;
    }

    private void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
