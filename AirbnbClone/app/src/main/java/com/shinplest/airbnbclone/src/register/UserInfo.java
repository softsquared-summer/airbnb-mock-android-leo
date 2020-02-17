package com.shinplest.airbnbclone.src.register;

public class UserInfo {
    private String name;
    private String lastName;
    private String birthday;
    private String email;

    public UserInfo(String name, String lastName, String birthday, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private String password;



}
