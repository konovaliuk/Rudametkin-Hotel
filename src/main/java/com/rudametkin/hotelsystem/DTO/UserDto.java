package com.rudametkin.hotelsystem.DTO;

import com.rudametkin.hotelsystem.Entitys.Role;
import com.rudametkin.hotelsystem.Entitys.User;

import java.util.List;

public class UserDto {

    private List<Role> roles;

    public UserWithRoles() {
        roles = null;
    }

    public UserWithRoles(User user, List<Role> roles) {
        super(user);
        this.roles = roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
