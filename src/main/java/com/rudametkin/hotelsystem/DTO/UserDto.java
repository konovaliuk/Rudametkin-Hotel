package com.rudametkin.hotelsystem.DTO;

import com.rudametkin.hotelsystem.Entitys.Role;
import com.rudametkin.hotelsystem.Entitys.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
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
}
