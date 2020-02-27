package com.shinplest.airbnbclone.src.house.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HouseResponse {
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

    public class Result{
        @SerializedName("image")
        private Image image;

        @SerializedName("info")
        private Info info;

        @SerializedName("facility")
        private ArrayList<Facility> facilities;

        @SerializedName("location")
        private Location location;

        public ArrayList<Facility> getFacilities() {
            return facilities;
        }

        public Location getLocation() {
            return location;
        }

        public Image getImage() {
            return image;
        }

        public Info getInfo() {
            return info;
        }
    }

    public class Image{
        @SerializedName("imageUrl")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }

    public class Info{
        @SerializedName("houseTitle")
        private String houseTitle;

        @SerializedName("hostImage")
        private String hostImage;

        @SerializedName("houseLocation")
        private String houseLocation;

        @SerializedName("houseHost")
        private String houseHost;

        @SerializedName("houseType")
        private String houseType;

        @SerializedName("houseSummary")
        private String houseSummary;

        @SerializedName("houseInfo")
        private String houseInfo;

        @SerializedName("houseDetail")
        private String houseDetail;

        @SerializedName("minimumStay")
        private String minimumStay;

        @SerializedName("checkIn")
        private String checkIn;

        @SerializedName("checkOut")
        private String checkOut;

        public String getHostImage() {
            return hostImage;
        }

        public String getHouseTitle() {
            return houseTitle;
        }

        public String getHouseLocation() {
            return houseLocation;
        }

        public String getHouseHost() {
            return houseHost;
        }

        public String getHouseType() {
            return houseType;
        }

        public String getHouseSummary() {
            return houseSummary;
        }

        public String getHouseInfo() {
            return houseInfo;
        }

        public String getHouseDetail() {
            return houseDetail;
        }

        public String getMinimumStay() {
            return minimumStay;
        }

        public String getCheckIn() {
            return checkIn;
        }

        public String getCheckOut() {
            return checkOut;
        }
    }

    public class Facility{
        @SerializedName("facilitisename")
        private String facilitisename;

        @SerializedName("facilitiseinfo")
        private String facilitiseinfo;

        public String getFacilitisename() {
            return facilitisename;
        }

        public String getFacilitiseinfo() {
            return facilitiseinfo;
        }
    }

    public class Location{
        @SerializedName("location")
        private String location;

        @SerializedName("longitude")
        private Float longitude;

        @SerializedName("latitude")
        private Float latitude;

        public String getLocation() {
            return location;
        }

        public Float getLongitude() {
            return longitude;
        }

        public Float getLatitude() {
            return latitude;
        }
    }
}
