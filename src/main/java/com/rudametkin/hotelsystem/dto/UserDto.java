package com.rudametkin.hotelsystem.dto;

import com.rudametkin.hotelsystem.entitys.Role;
import com.rudametkin.hotelsystem.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String login;
    private String password;
    private String userInfo;
    private List<Role> roles;

    public UserDto(User user, List<Role> roles) {
        name = user.getName();
        surname = user.getSurname();
        phone = user.getPhone();
        email = user.getEmail();
        login = user.getLogin();
        password = user.getPassword();
        userInfo = user.getUserInfo();
        this.roles = roles;
    }

    public boolean hasRole(String name) {
        for (Role role : roles)
            if (role.getName().equals(name))
                return true;
        return false;
    }
}
