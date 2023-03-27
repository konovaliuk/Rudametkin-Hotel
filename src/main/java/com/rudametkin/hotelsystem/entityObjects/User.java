package com.rudametkin.hotelsystem.entityObjects;

public class User {
    private int id = -1;
    private String name;
    private String surname;
    private String phone;
    private String login;
    private String email;
    private String password;
    private String info = null;
    private void initValues(int id, String name, String surname, String phone, String email, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.login = login;
    }
    public User() {}
    public User(String name, String surname, String phone, String email, String login, String password) {
        initValues(-1, name, surname, phone, email, login, password);
    }
    public User(int id, String name, String surname, String phone, String email, String login, String password) {
        initValues(id, name, surname, phone, email, login, password);
    }
    public int getId()  {
        return id;
    }
    public String getName()  {
        return name;
    }
    public String getSurname()  {
        return surname;
    }
    public String getPhone()  {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getLogin() { return login; }
    public String getPassword() {return password;}
    public String getInfo() { return new String(info); }


    public void setName(String newName)
    {
        name = newName;
    }
    public void setSurname(String newSurname)
    {
        surname = newSurname;
    }
    public void setPhone(String newPhone)
    {
        phone = newPhone;
    }
    public void setEmail(String newEmail)
    {
        email = newEmail;
    }
    public void setLogin(String newLogin) { login = newLogin; }
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }
    public void setInfo(String newInfo) { info = newInfo; }
}
