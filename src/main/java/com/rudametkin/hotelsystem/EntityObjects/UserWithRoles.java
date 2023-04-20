package com.rudametkin.hotelsystem.EntityObjects;

import java.util.List;

public class UserWithRoles extends User {

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
