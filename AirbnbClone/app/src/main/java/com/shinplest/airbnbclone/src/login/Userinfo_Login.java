package com.shinplest.airbnbclone.src.login;



public class Userinfo_Login {

    private String email;
    private String pw;

    public Userinfo_Login(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }


    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }
}
