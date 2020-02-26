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
        private ArrayList<Image> images;

        @SerializedName("info")
        private Info info;

        public ArrayList<Image> getImages() {
            return images;
        }

        public Info getInfo() {
            return info;
        }
    }

    public class Image{
        @SerializedName("no")
        private int no;
        @SerializedName("image")
        private String imgUrl;

        public int getNo() {
            return no;
        }

        public String getImgUrl() {
            return imgUrl;
        }
    }

    public class Info{
        @SerializedName("houseTitle")
        private String houseTitle;

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
}
//
//
//@SerializedName("")
//private String
