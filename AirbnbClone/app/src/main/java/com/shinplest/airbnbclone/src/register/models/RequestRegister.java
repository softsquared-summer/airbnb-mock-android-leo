package com.shinplest.airbnbclone.src.register.models;


import com.google.gson.annotations.SerializedName;

public class RequestRegister {

    @SerializedName("phone")
    private String phone;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("email")
    private String email;
    @SerializedName("pw")
    private String pw;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
