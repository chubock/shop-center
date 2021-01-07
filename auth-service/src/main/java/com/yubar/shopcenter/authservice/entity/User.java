package com.yubar.shopcenter.authservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
public class User implements Serializable, UserDetails {

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36)
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

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(getAuthority().name()));
    }
}