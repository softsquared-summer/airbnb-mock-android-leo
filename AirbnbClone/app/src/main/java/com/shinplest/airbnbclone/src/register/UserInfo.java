package com.shinplest.airbnbclone.src.register;


public class UserInfo {
    private String phone;
    private String last_name;
    private String first_name;
    private String birthday;
    private String email;
    private String pw;


    public UserInfo(String phone, String last_name, String first_name, String birthday, String email, String pw) {
        this.phone = phone;
        this.last_name = last_name;
        this.first_name = first_name;
        this.birthday = birthday;
        this.email = email;
        this.pw = pw;
    }

    public UserInfo(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }

    public String getPhone() {
        return phone;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }
}
