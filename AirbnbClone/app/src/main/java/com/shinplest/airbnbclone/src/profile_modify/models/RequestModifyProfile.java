package com.shinplest.airbnbclone.src.profile_modify.models;

import com.google.gson.annotations.SerializedName;

public class RequestModifyProfile {
    @SerializedName("image")
    private String image;

    @SerializedName("info")
    private String info;

    @SerializedName("location")
    private String location;

    @SerializedName("school")
    private String school;

    @SerializedName("job")
    private String job;

    @SerializedName("language")
    private String language;

    public void setImage(String image) {
        this.image = image;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getImage() {
        return image;
    }

    public String getInfo() {
        return info;
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
