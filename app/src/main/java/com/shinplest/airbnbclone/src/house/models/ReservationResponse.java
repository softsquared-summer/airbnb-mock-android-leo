package com.shinplest.airbnbclone.src.house.models;

import com.google.gson.annotations.SerializedName;

public class ReservationResponse {
    @SerializedName("result")
    private String result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
