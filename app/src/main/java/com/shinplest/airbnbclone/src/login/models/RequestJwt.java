package com.shinplest.airbnbclone.src.login.models;

import com.google.gson.annotations.SerializedName;

public class RequestJwt {

    @SerializedName("email")
    private String email;

    @SerializedName("pw")
    private String pw;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
