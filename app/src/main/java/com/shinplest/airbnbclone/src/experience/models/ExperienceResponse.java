package com.shinplest.airbnbclone.src.experience.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExperienceResponse {

    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public Result getResult() {
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

    public class Result {
        @SerializedName("image")
        private ArrayList<Image> image;

        @SerializedName("info")
        private Info info;

        @SerializedName("item")
        private ArrayList<Item> item;

        @SerializedName("location")
        private Location location;

        public ArrayList<Image> getImage() {
            return image;
        }

        public Info getInfo() {
            return info;
        }

        public ArrayList<Item> getItem() {
            return item;
        }

        public Location getLocation() {
            return location;
        }
    }

    public class Image {
        @SerializedName("imageUrl")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public class Info {
        @SerializedName("experienceNo")
        private String experienceNo;

        @SerializedName("title")
        private String title;

        @SerializedName("evaluation")
        private String evaluation;

        @SerializedName("juso")
        private String juso;

        @SerializedName("category")
        private String category;

        @SerializedName("playtime")
        private String playtime;

        @SerializedName("personnel")
        private String personnel;

        @SerializedName("inclusion")
        private String inclusion;

        @SerializedName("offerLanguage")
        private String offerLanguage;

        @SerializedName("program")
        private String program;

        @SerializedName("hostImage")
        private String hostImage;

        @SerializedName("hostName")
        private String hostName;

        @SerializedName("hostIntroduce")
        private String hostIntroduce;

        @SerializedName("guestprerequistie")
        private String guestprerequistie;

        public String getExperienceNo() {
            return experienceNo;
        }

        public String getTitle() {
            return title;
        }

        public String getEvaluation() {
            return evaluation;
        }

        public String getPersonnel() {
            return personnel;
        }

        public String getJuso() {
            return juso;
        }

        public String getCategory() {
            return category;
        }

        public String getPlaytime() {
            return playtime;
        }

        public String getInclusion() {
            return inclusion;
        }

        public String getOfferLanguage() {
            return offerLanguage;
        }

        public String getProgram() {
            return program;
        }

        public String getHostImage() {
            return hostImage;
        }

        public String getHostName() {
            return hostName;
        }

        public String getHostIntroduce() {
            return hostIntroduce;
        }

        public String getGuestprerequistie() {
            return guestprerequistie;
        }
    }

    public class Item {
        @SerializedName("offeritemNo")
        private String offeritemNo;

        @SerializedName("items")
        private String items;

        @SerializedName("tag")
        private String tag;

        public String getOfferitemNo() {
            return offeritemNo;
        }

        public String getItems() {
            return items;
        }

        public String getTag() {
            return tag;
        }
    }

    public class Location {

        @SerializedName("sido")
        private String sido;

        @SerializedName("info")
        private String info;

        @SerializedName("longitude")
        private String longitude;

        @SerializedName("latitude")
        private String latitude;

        public String getSido() {
            return sido;
        }

        public String getInfo() {
            return info;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }
    }
}
