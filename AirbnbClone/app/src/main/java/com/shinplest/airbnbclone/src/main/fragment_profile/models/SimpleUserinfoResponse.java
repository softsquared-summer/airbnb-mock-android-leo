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
        @SerializedName("firstName")
        private String firstName;

        @SerializedName("image")
        private String profileImgUrl;

        public String getFirstName() {
            return firstName;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }
    }
}
