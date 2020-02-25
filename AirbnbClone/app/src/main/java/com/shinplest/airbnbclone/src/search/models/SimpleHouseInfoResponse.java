package com.shinplest.airbnbclone.src.search.models;

import com.google.gson.annotations.SerializedName;

public class SimpleHouseInfoResponse {
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
        @SerializedName("houseNo")
        private int houseNo;

        @SerializedName("houseInfo")
        private String houseInfo;

        @SerializedName("starAvg")
        private String starAvg;

        @SerializedName("reviewCnt")
        private int reviewCnt;

        @SerializedName("houseName")
        private String  houseName;

        @SerializedName("houseImages")
        private String houseImages;

        public int getHouseNo() {
            return houseNo;
        }

        public String getHouseInfo() {
            return houseInfo;
        }

        public String getStarAvg() {
            return starAvg;
        }

        public int getReviewCnt() {
            return reviewCnt;
        }

        public String getHouseName() {
            return houseName;
        }

        public String getHouseImages() {
            return houseImages;
        }
    }
}
