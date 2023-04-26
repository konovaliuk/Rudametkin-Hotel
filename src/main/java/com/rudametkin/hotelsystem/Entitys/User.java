package com.rudametkin.hotelsystem.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String login;
    private String password;
    @Column(name="user_info")
    private String userInfo;

    public User(String name, String surname, String phone, String email, String login, String password, String userInfo) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.userInfo = userInfo;
    }

    public String toString() {
        return "User { " + name + ", " + surname + ", " + login + ", " + password + " } ";
    }
}
