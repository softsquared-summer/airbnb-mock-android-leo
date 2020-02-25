package com.shinplest.airbnbclone.src.profile.models;

import androidx.annotation.StringRes;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
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

        @SerializedName("info")
        private String myinfo;

        @SerializedName("createdDate")
        private String createdDate;

        @SerializedName("location")
        private String location;

        @SerializedName("school")
        private String school;

        @SerializedName("job")
        private String job;

        @SerializedName("language")
        private String language;

        public String getFirstName() {
            return firstName;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }

        public String getMyinfo() {
            return myinfo;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getLocation() {
            return location;
        }

        public String getSchool() {
            return school;
        }

        public String getJob() {
            return job;
        }

        public String getLanguage() {
            return language;
        }

    }
}
