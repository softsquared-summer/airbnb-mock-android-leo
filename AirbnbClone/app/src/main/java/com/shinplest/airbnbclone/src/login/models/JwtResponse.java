package com.shinplest.airbnbclone.src.login.models;

import com.google.gson.annotations.SerializedName;

public class JwtResponse {
    @SerializedName("result")
    private Result result;

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
