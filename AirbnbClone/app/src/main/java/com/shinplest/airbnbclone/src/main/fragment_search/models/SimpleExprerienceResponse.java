package com.shinplest.airbnbclone.src.main.fragment_search.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SimpleExprerienceResponse {

    @SerializedName("result")
    private ArrayList<Result> result;

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

    public boolean isSuccess() {
        return isSuccess;
    }

    public ArrayList<Result> getResult() {
        return result;
    }

    public class Result {
        @SerializedName("repImage")
        private String repImage;

        @SerializedName("categoryName")
        private String categoryName;

        @SerializedName("experienceTitle")
        private String experienceTitle;

        @SerializedName("experiencePrice")
        private String experiencePrice;

        @SerializedName("starAvg")
        private String starAvg;

        @SerializedName("reviewcnt")
        private String reviewcnt;

        @SerializedName("experienceInfo")
        private String experienceInfo;

        public String getRepImage() {
            return repImage;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getExperienceTitle() {
            return experienceTitle;
        }

        public String getExperiencePrice() {
            return experiencePrice;
        }

        public String getStarAvg() {
            return starAvg;
        }

        public String getReviewcnt() {
            return reviewcnt;
        }

        public String getExperienceInfo() {
            return experienceInfo;
        }
    }


}
