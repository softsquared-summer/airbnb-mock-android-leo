package com.shinplest.airbnbclone.src.main.models;
import com.google.gson.annotations.SerializedName;
import com.shinplest.airbnbclone.src.Result;

public class DefaultResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}