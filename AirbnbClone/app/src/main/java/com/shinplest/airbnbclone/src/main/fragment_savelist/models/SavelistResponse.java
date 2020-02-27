package com.shinplest.airbnbclone.src.main.fragment_savelist.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SavelistResponse {
    @SerializedName("result")
    private ArrayList<Result> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public ArrayList<Result> getResult() {
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
        @SerializedName("houseNo")
        private int houseNo;

        @SerializedName("houseInfo")
        private String houseInfo;

        @SerializedName("message")
        private String message;

        @SerializedName("starAvg")
        private String starAvg;

        @SerializedName("reviewCnt")
        private String reviewCnt;

        @SerializedName("houseName")
        private String houseName;

        @SerializedName("housePrice")
        private String housePrice;

        @SerializedName("images")
        private String images;

        @SerializedName("isSave")
        private int isSave;

        public int getHouseNo() {
            return houseNo;
        }

        public String getHouseInfo() {
            return houseInfo;
        }

        public String getMessage() {
            return message;
        }

        public String getStarAvg() {
            return starAvg;
        }

        public String getReviewCnt() {
            return reviewCnt;
        }

        public String getHouseName() {
            return houseName;
        }

        public String getHousePrice() {
            return housePrice;
        }

        public String getImages() {
            return images;
        }

        public int getIsSave() {
            return isSave;
        }
    }


}
