package com.shinplest.airbnbclone.src.main.models;

import com.google.gson.annotations.SerializedName;

public class UserNoResponse {
    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Result getResult() {
        return result;
    }

    public class Result{
        @SerializedName("userNo")
        private int userNo;

        public int getUserNo() {
            return userNo;
        }
    }
}
