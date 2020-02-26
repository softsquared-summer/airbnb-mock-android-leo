package com.shinplest.airbnbclone.src.search.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExistLocationResponse {
    @SerializedName("result")
    private ArrayList<String> results;

    @SerializedName("isSuccess")
    private String isSuccess;

    @SerializedName("code")
    private String code;

    @SerializedName("messsage")
    private String messsage;

    public ArrayList<String> getResults() {
        return results;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public String getCode() {
        return code;
    }

    public String getMesssage() {
        return messsage;
    }

}
