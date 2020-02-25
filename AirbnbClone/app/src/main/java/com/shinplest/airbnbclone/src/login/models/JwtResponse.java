package com.shinplest.airbnbclone.src.login.models;

import com.google.gson.annotations.SerializedName;

public class JwtResponse {
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

    public Result getResult() {
        return result;
    }

    public class Result{
        @SerializedName("jwt")
        private String jwt;

        public String getJwt() {
            return jwt;
        }
    }
}
