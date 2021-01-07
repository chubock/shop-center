package com.yubar.shopcenter.userservice.model;

import com.yubar.shopcenter.userservice.entity.Authority;
import com.yubar.shopcenter.userservice.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

    private String id;
    private String username;
    private String password;
    private Authority authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public static UserModel modelOf(User user) {
        UserModel model = new UserModel();
        BeanUtils.copyProperties(user, model);
        model.setPassword(null);
        return model;
    }
}
