package com.shinplest.airbnbclone.src.main.fragment_profile.models;

import com.google.gson.annotations.SerializedName;

public class SimpleUserinfoResponse {
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

    public boolean isSuccess() {
        return isSuccess;
    }

    public class Result {
        @SerializedName("last_name")
        private String last_name;

        @SerializedName("first_name")
        private String first_name;

        public String getLast_name() {
            return last_name;
        }

        public String getFirst_name() {
            return first_name;
        }
    }
}
