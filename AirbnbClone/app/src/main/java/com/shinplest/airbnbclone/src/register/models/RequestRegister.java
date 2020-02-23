package com.shinplest.airbnbclone.src.register.models;


import com.google.gson.annotations.SerializedName;

public class RequestRegister {

    @SerializedName("phone")
    private String phone;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("email")
    private String email;
    @SerializedName("pw")
    private String pw;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
